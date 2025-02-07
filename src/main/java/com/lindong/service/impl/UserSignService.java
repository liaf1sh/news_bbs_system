package com.lindong.service.impl;

import com.lindong.dao.IUserSignDao;
import com.lindong.domain.Integral;
import com.lindong.domain.UserSign;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IUserSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserSignService implements IUserSignService {

    @Autowired
    private IUserSignDao userSignDao;

    @Override
    public boolean updateUserSign(UserSign userSign) {
        int i = userSignDao.updateUserSign(userSign);
        if (i <= 0){
            throw new CustomException(ResultCode.SIGN_ERROR);
        }
        return true;
    }

    @Override
    public Integral selectIntegral(Integer uid) {
        return userSignDao.selectIntegral(uid);
    }

    @Override
    public boolean updateUserIntegral(Integral integral) {
        int i = userSignDao.updateUserIntegral(integral);
        if (i <= 0){
            throw new CustomException(ResultCode.UPDATE__ERROR);
        }
        return true;
    }

    @Override
    public List<Map> selectPostCountByDate() {
        return userSignDao.selectPostCountByDate();
    }

    @Override
    public int selectPostCount() {
        return userSignDao.selectPostCount();
    }

    @Override
    public int selectUserCount() {
        return userSignDao.selectUserCount();
    }

    @Override
    public int selectTodaySignCount() {
        return userSignDao.selectTodaySignCount();
    }

    @Override
    public List<Object> paging(Map<String, Object> param) {
        int pageSize = (Integer) param.get("pageSize");
        int index = ((Integer)param.get("pageNo")-1) * pageSize;
        param.put("index",index);
        return userSignDao.paging(param);
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return userSignDao.getCount(param);
    }

    @Override
    public Object getObjectById(Integer id) {
        return userSignDao.getObjectById(id);
    }

    @Override
    public Object getObjectByName(String name) {
        return userSignDao.getObjectByName(name);
    }
}
