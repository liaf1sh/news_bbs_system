package com.lindong.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUserRoleDao extends IBaseDao{


    /**
     * 修改用户角色信息
     * @param map
     * @return
     */
    int alterUserRole(Map<String, Integer> map);

    /**
     * 添加用户角色
     * @return
     */
    int insertUserRole(Map<String, Integer> map);

    /**
     * 删除用户角色
     * @param ids
     * @return
     */
    int deleteUserRole(@Param("ids") List<Integer> ids);


}
