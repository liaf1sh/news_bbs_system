package com.lindong.service.impl;

import com.lindong.dao.IUserDao;
import com.lindong.domain.FriendGroup;
import com.lindong.domain.User;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IUserService;
import com.lindong.utils.shiro.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User getPwdSalt(String username) {
        User user = userDao.getPwdSalt(username);
        return user;
    }

    @Override
    public boolean login(Map<String, String> param) {
        User user = this.getPwdSalt(param.get("username"));
        if (user != null){
            String password = MD5.getMd5String(param.get("password"),user.getSalt());
            if (password.equals(user.getPassword())){
                if (user.getUser_status().equals("1")){
                    /*User user2 = new User();
                    //更新操作,记录登录时间以及登录ip,增加登录次数
                    user2.setId(user.getId());
                    user2.setLast_login_ip(param.get("last_login_ip"));
                    user2.setLast_login_time(new Date());*/
                    param.remove("password");
                    param.put("id",""+user.getId());
                    param.put("last_login_time","1");
                    //user2.setOnline_status("1");
                    int count = userDao.updateUser(param);
                    if (count > 0){
                        System.out.println("登录更新成功");
                        return true;
                    }
                }else {
                    throw new CustomException(ResultCode.USER_DISABLE);
                }
            }
            throw new CustomException(ResultCode.PASSWORD_ERROR);
        }else {
            throw new CustomException(ResultCode.USERNAME_ERROR);
        }
    }

    @Override
    public boolean updateUser(Map<String,String> params) {
        int i = userDao.updateUser(params);
        if (i == 0){
            throw new CustomException(ResultCode.UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public User findByName(String username) {
        return userDao.findByName(username);
    }

    @Override
    public List<User> listUser(List<Integer> ids) {
        return userDao.listUser(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean register(Map<String,String> map) {
        int count = userDao.addUser(map);
        Integer uid = this.findByName(map.get("username")).getId();
        int i = userDao.addIntegral(uid);
        int i2 = userDao.addExperience(uid);
        int i3 = userDao.addSign(uid);
        List<FriendGroup> list = new ArrayList<>();
        for (int j = 0;j < 4; j++){
            FriendGroup friendGroup = new FriendGroup();
            if (j == 0){
                friendGroup.setGroup_name("同学们~");
            }else if (j == 1){
                friendGroup.setGroup_name("  ╲◢ ╲◢");
            }else if (j == 2){
                friendGroup.setGroup_name("  兄弟们 ^`");
            }else {
                friendGroup.setGroup_name("  家人们`~");
            }
            friendGroup.setUser_id(uid);
            list.add(friendGroup);
        }
        int i4 = userDao.insertFriendGroup(list);
        if (count == 0 || i == 0 || i2 == 0 || i3 == 0 || i4 == 0){
            throw new CustomException(ResultCode.REGISTER_ERROR);
        }
        return true;
    }

    @Override
    public int getUserCount() {
        return userDao.getUserCount();
    }


}
