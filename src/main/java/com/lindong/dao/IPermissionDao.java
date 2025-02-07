package com.lindong.dao;

import com.lindong.domain.Permission;

import java.util.List;

public interface IPermissionDao {

    /**
     * 根据用户名查询该用户所有权限
     * @param userName
     * @return
     */
    List<Permission> listPermissionsByUserName(String userName);

}
