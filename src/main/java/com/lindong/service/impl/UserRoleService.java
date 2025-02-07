package com.lindong.service.impl;

import com.lindong.dao.IUserRoleDao;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IUserRoleService;
import com.lindong.utils.shiro.DatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserRoleService implements IUserRoleService {

    @Autowired
    private IUserRoleDao userRoleDao;
    @Autowired
    private DatabaseRealm databaseRealm;

    @Override
    public boolean alterUserRole(Map<String,Integer> map) {
        int i = userRoleDao.alterUserRole(map);
        if (i == 0){
            throw new CustomException(ResultCode.UPDATE_FAILED);
        }
        databaseRealm.clearCached();    //清除角色缓存
        return true;
    }

    @Override
    public boolean insertUserRole(Map<String,Integer> map) {
        int i = userRoleDao.insertUserRole(map);
        if (i == 0){
            throw new CustomException(ResultCode.SAVE_FAILED);
        }
        databaseRealm.clearCached();    //清除角色缓存
        return true;
    }

    @Override
    public boolean deleteUserRole(List<Integer> ids) {
        int i = userRoleDao.deleteUserRole(ids);
        if (i == 0){
            throw new CustomException(ResultCode.DELETE_ERROR);
        }
        databaseRealm.clearCached();    //清除角色缓存
        return true;
    }


    @Override
    public List<Object> paging(Map<String, Object> param) {
        Integer pageNo = (Integer) param.get("pageNo");
        Integer pageSize = (Integer) param.get("pageSize");
        param.put("index",(pageNo - 1) * pageSize);
        return userRoleDao.paging(param);
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return userRoleDao.getCount(param);
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
