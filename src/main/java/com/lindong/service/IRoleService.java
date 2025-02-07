package com.lindong.service;

import com.lindong.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IRoleService extends IBaseService {

    /**
     * 根据用户名获取所有角色集合
     * @param userName
     * @return
     */
    Set<String> listRoles(String userName);


    /**
     * 修改角色信息
     * @param role
     * @return
     */
    boolean alterRole(Role role);

    /**
     * 添加角色
     * @return
     */
    boolean insertRole(Map<String,String> map);

    /**
     * 删除角色
     * @param ids
     * @return
     */
    boolean deleteRole(@Param("ids") List<Integer> ids);



}
