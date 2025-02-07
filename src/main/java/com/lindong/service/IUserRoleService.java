package com.lindong.service;

import com.lindong.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IUserRoleService extends IBaseService {

    /**
     * 修改角色信息
     * @param map
     * @return
     */
    boolean alterUserRole(Map<String, Integer> map);

    /**
     * 添加角色
     * @return
     */
    boolean insertUserRole(Map<String, Integer> map);

    /**
     * 删除角色
     * @param ids
     * @return
     */
    boolean deleteUserRole(@Param("ids") List<Integer> ids);



}
