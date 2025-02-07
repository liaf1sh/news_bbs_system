package com.lindong.service.impl;

import com.lindong.dao.IPostShareDao;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IPostShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PostShareService implements IPostShareService {

    @Autowired
    private IPostShareDao postShareDao;

    @Override
    public boolean selectPostShareExist(Map<String, String> params) {
        int i = postShareDao.selectPostShareExist(params);
        if (i > 0){
            throw new CustomException(ResultCode.POST_SHARE_EXIST);
        }
        return true;
    }

    @Override
    public boolean insertPostShare(Map<String, String> map) {
        int i = postShareDao.insertPostShare(map);
        if (i == 0){
            throw new CustomException(ResultCode.POST_SHARE_ERROR);
        }
        return true;
    }
}




















