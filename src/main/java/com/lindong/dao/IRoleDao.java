package com.lindong.dao;


import com.lindong.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IRoleDao extends IBaseDao{

    /**
     * 根据用户名查询该用户所有角色
     * @param userName
     * @return
     */
    List<Role> listRolesByUserName(String userName);


    /**
     * 修改角色信息
     * @param role
     * @return
     */
    int alterRole(Role role);

    /**
     * 添加角色
     * @return
     */
    int insertRole(Map<String,String> map);

    /**
     * 删除角色
     * @param ids
     * @return
     */
    int deleteRole(@Param("ids") List<Integer> ids);



}
