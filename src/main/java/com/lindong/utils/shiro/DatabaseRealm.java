package com.lindong.utils.shiro;


import com.lindong.domain.User;
import com.lindong.service.IRoleService;
import com.lindong.service.IUserService;
import com.lindong.service.impl.RoleService;
import com.lindong.service.impl.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Set;

public class DatabaseRealm extends AuthorizingRealm {

    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;
    /*@Resource
    private PermissionService permissionService;*/

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取账号密码
        UsernamePasswordToken t = (UsernamePasswordToken) token;
        String userName = token.getPrincipal().toString();
        String password = new String(t.getPassword());
        //获取数据库中的密码
        User user = userService.getPwdSalt(userName);
        String saltDB = user.getSalt();
        //对密码进行MD5加密
        String md5String = MD5.getMd5String(password,saltDB);

        //如果为空就是账号不存在，如果不相同就是密码错误，但是都抛出AuthenticationException，而不是抛出具体错误原因，免得给破解者提供帮助信息
        if (null == user.getPassword() || !user.getPassword().equals(md5String)){
            throw new AuthenticationException();
        }
        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, password, getName());
        return simpleAuthenticationInfo;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //进入到这里，表示账号已经通过验证了
        String userName = (String) principalCollection.getPrimaryPrincipal();
        //通过service获取角色和权限
        Set<String> roles = roleService.listRoles(userName);
        //System.out.println("================roles"+roles.toString());
        // Set<String> permissions = permissionService.listPermissions(userName);
        //System.out.println("======================pms"+permissions.toString());
        //授权对象
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        //把通过service获取到的角色和权限放进去
        //s.setStringPermissions(permissions);
        s.setRoles(roles);
        return s;
    }

    /**
     * 清空授权缓存,用于在修改权限后,一般在service中注入DatabaseRealm类调用
     */
    public void clearCached(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }


}