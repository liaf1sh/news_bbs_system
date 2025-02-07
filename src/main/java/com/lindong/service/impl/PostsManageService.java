package com.lindong.service.impl;

import com.lindong.dao.IPostReportDao;
import com.lindong.dao.IPostsManageDao;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IPostsManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PostsManageService implements IPostsManageService {

    @Autowired
    private IPostsManageDao postsManageDao;
    @Autowired
    private IPostReportDao postReportDao;

    @Override
    public List<Object> paging(Map<String, Object> param) {
        Integer pageNo = (Integer) param.get("pageNo");
        Integer pageSize = (Integer) param.get("pageSize");
        Integer index = (pageNo - 1) * pageSize;
        param.put("index",index);
        List<Object> data = postsManageDao.paging(param);
        return data;
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return postsManageDao.getCount(param);
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
    public boolean deletePosts(List<Integer> ids) {
        int i = postsManageDao.deletePosts(ids);
        if (i == 0){
            throw new CustomException(ResultCode.DELETE_ERROR);
        }
        return true;
    }

    @Transactional(rollbackFor=CustomException.class)
    @Override
    public boolean alterPosts(Map<String, Object> params) {
        int i1 = postReportDao.insertWarnList(params);
        int i2 = postsManageDao.alterPosts(params);
        if (i1 == 0 || i2 == 0){
            throw new CustomException(ResultCode.UPDATE_FAILED);
        }
        return true;
    }

}
