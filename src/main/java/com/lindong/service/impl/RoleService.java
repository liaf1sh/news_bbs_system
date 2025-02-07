package com.lindong.service.impl;

import com.lindong.dao.IRoleDao;
import com.lindong.domain.Role;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public Set<String> listRoles(String userName) {
        List<Role> roles = roleDao.listRolesByUserName(userName);
        Set<String> result = new HashSet<String>();
        for (Role role: roles) {
            result.add(role.getRole_name());
        }
        return result;
    }


    @Override
    public boolean alterRole(Role role) {
        int i = roleDao.alterRole(role);
        if (i == 0){
            throw new CustomException(ResultCode.UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public boolean insertRole(Map<String,String> map) {
        int i = roleDao.insertRole(map);
        if (i == 0){
            throw new CustomException(ResultCode.SAVE_FAILED);
        }
        return true;
    }

    @Override
    public boolean deleteRole(List<Integer> ids) {
        int i = roleDao.deleteRole(ids);
        if (i == 0){
            throw new CustomException(ResultCode.DELETE_ERROR);
        }
        return true;
    }

    @Override
    public List<Object> paging(Map<String, Object> param) {
        if (param.get("currentPage") != null){
            int currentPage = (Integer)param.get("currentPage");
            int pageSize = (Integer)param.get("pageSize");
            int index = (currentPage - 1) * pageSize;
            param.put("index",index);
        }
        return roleDao.paging(param);
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return roleDao.getCount(param);
    }

    @Override
    public Object getObjectById(Integer id) {
        return null;
    }

    @Override
    public Object getObjectByName(String name) {
        return null;
    }
}
