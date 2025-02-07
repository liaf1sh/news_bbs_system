package com.lindong.service.impl;

import com.lindong.dao.IUserCollectDao;
import com.lindong.domain.UserCollect;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IUserCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserCollectService implements IUserCollectService {

    @Autowired
    private IUserCollectDao userCollectDao;

    @Override
    public boolean insertUserCollect(UserCollect userCollect) {
        int i = userCollectDao.insertUserCollect(userCollect);
        if(i <= 0){
            throw new CustomException(ResultCode.POST_COLLECT_FAILED);
        }
        return true;
    }

    @Override
    public boolean selectCollectCountByMap(Map map) {
        int i = userCollectDao.selectCollectCountByMap(map);
        if (i > 0){    //帖子已收藏过了
            throw new CustomException(ResultCode.POST_COLLECT_ERROR);
        }
        return true;
    }

    @Override
    public boolean deleteCollect(List<Integer> ids) {
        int i = userCollectDao.deleteCollect(ids);
        if (i == 0){
            throw new CustomException(ResultCode.DELETE_ERROR);
        }
        return true;
    }

    @Override
    public List<Object> paging(Map<String, Object> param) {
        int pageSize = (Integer) param.get("pageSize");
        int pageNo = (Integer) param.get("pageNo");
        int index = (pageNo - 1) * pageSize;
        param.put("index",index);
        return userCollectDao.paging(param);
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return userCollectDao.getCount(param);
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
