package com.lindong.service;

import java.util.Set;

public interface IPermissionService {

    /**
     * 根据用户名获取该用户所有权限集合
     * @param userName
     * @return
     */
    Set<String> listPermissions(String userName);

}
