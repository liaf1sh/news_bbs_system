package com.lindong.service.impl;

import com.lindong.dao.IUserManageDao;
import com.lindong.domain.User;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IBaseService;
import com.lindong.service.IUserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
public class UserManageService implements IUserManageService {
    @Resource
    private IUserManageDao userManageDao;
    @Override
    public List<Object> paging(Map<String, Object> param) {
        int pageCurrent = (Integer)param.get("pageCurrent"); //当前页
        int pageSize = (Integer)param.get("pageSize");  //页数
        int pageStart = (pageCurrent-1)*pageSize;
        param.put("pageStart",pageStart);
        List<Object> users = userManageDao.paging(param);
        return users;
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return userManageDao.getCount(param);
    }

    @Override
    public Object getObjectById(Integer id) {
        return null;
    }

    @Override
    public Object getObjectByName(String name) {
        return null;
    }

    @Override
    public boolean updateUserState(Integer userState, Integer userId) {
        Integer count =userManageDao.updateUserState(userState,userId);
        if (count<1){
            throw new CustomException(ResultCode.UPDATE_FAILED);
        }
        return true;
    }

}
