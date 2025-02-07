package com.lindong.service.impl;

import com.lindong.dao.IAttentionDao;
import com.lindong.domain.Post;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AttentionService implements IAttentionService {

    @Autowired
    private IAttentionDao attentionDao;

    @Override
    public List<Object> paging(Map<String, Object> param) {
        int pageNo = (Integer) param.get("pageNo");
        int pageSize = (Integer) param.get("pageSize");
        int index = (pageNo - 1) * pageSize;
        param.put("index",index);
        return attentionDao.paging(param);
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return attentionDao.getCount(param);
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
    public boolean deleteAttention(Integer uid, Integer aid) {
        int i = attentionDao.deleteAttention(uid, aid);
        if (i == 0){
            throw new CustomException(ResultCode.UNFOLLOW_FAILED);
        }
        return true;
    }

    @Override
    public List<Post> getCarePosts(Integer uid) {
        return attentionDao.getCarePosts(uid);
    }

    @Override
    public boolean selectCare(Map<String, Integer> map) {
        int i = attentionDao.selectCare(map);
        if (i == 0){
            throw new CustomException(ResultCode.CARE_NOT);
        }
        return true;
    }
}
