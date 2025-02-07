package com.lindong.service.impl;

import com.lindong.dao.IPermissionDao;
import com.lindong.domain.Permission;
import com.lindong.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public Set<String> listPermissions(String userName) {
        List<Permission> permissions = permissionDao.listPermissionsByUserName(userName);
        Set<String> result = new HashSet<>();
        for (Permission permission: permissions) {
            result.add(permission.getPermission_name());
        }
        return result;
    }


}
