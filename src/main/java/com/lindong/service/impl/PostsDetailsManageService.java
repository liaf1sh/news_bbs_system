package com.lindong.service.impl;

import com.lindong.dao.IPostsDetailsManageDao;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IPostsDetailsManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostsDetailsManageService implements IPostsDetailsManageService {

    @Autowired
    private IPostsDetailsManageDao postsDetailsManageDao;

    @Override
    public boolean deletePostsDetails(List<Integer> ids) {
        int i = postsDetailsManageDao.deletePostsDetails(ids);
        if (i == 0){
            throw new CustomException(ResultCode.DELETE_ERROR);
        }
        return true;
    }

    @Override
    public List<Object> paging(Map<String, Object> param) {
        Integer currentPage = (Integer) param.get("currentPage");
        Integer pageSize = (Integer) param.get("pageSize");
        Integer index = (currentPage - 1) * pageSize;
        param.put("index",index);
        List<Object> data = postsDetailsManageDao.paging(param);
        return data;
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return postsDetailsManageDao.getCount(param);
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
