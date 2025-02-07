package com.lindong.service.impl;

import com.lindong.dao.IPostBrowseCommentDao;
import com.lindong.dao.IPostReportDao;
import com.lindong.domain.Grade;
import com.lindong.domain.Post;
import com.lindong.domain.PostDetails;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IPostBrowseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostBrowseCommentService implements IPostBrowseCommentService {

    @Autowired
    private IPostBrowseCommentDao postDao;

    @Override
    public List<Post> selectNewestPosts(Map<String, String> params) {
        return postDao.selectNewestPosts(params);
    }

    @Override
    public int getThemeCountById(Integer uid) {
        return postDao.getThemeCountById(uid);
    }

    @Override
    public int getPostsCountById(Integer uid) {
        return postDao.getPostsCountById(uid);
    }

    @Override
    public int getExperienceById(Integer uid) {
        return postDao.getExperienceById(uid);
    }

    @Override
    public Grade getGrade(Integer experience) {
        return postDao.getGrade(experience);
    }

    @Override
    public boolean insertPostDetails(PostDetails postDetails) {
        int i = postDao.insertPostDetails(postDetails);
        if (i <= 0){
            throw new CustomException(ResultCode.POST_REPLY_FAILED);
        }
        return true;
    }

    @Override
    public boolean updatePost(Map<String, String> params) {
        int i = postDao.updatePost(params);
        if (i <= 0){
            throw new CustomException(ResultCode.POST_UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public boolean selectThemeOperation(Map<String,Integer> map) {
        return postDao.selectThemeOperation(map)>0?true:false;
    }

    @Override
    public boolean insertThemeOperation(Map<String,Integer> map) {
        int i = postDao.insertThemeOperation(map);
        if (i == 0){
            throw new CustomException(ResultCode.OPERATION_ERROR);
        }
        return true;
    }

    @Override
    public boolean selectAttention(Integer uid, Integer aid) {
        int i = postDao.selectAttention(uid, aid);
        if (i > 0){
            throw new CustomException(ResultCode.CARE_FAILED);
        }
        return true;
    }

    @Override
    public boolean insertAttention(Integer uid, Integer aid) {
        this.selectAttention(uid,aid);
        int i = postDao.insertAttention(uid, aid);
        if (i == 0){
            throw new CustomException(ResultCode.CARE_ERROR);
        }
        return true;
    }

    @Override
    public List<Object> paging(Map<String, Object> param) {
        int currentPage = (Integer) param.get("currentPage");
        int pageSize = (Integer) param.get("pageSize");
        int index = (currentPage - 1) * pageSize;
        param.put("index",index);
        List<Object> posts = postDao.paging(param);
        return posts;
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return postDao.getCount(param);
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
    public Integer clickAddBrowseCount(Integer id) {
        return postDao.clickAddBrowseCount(id);
    }

    @Override
    public Map getSearchPosts(Map map) {
        int pageCurrent = (Integer)map.get("currentPage"); //当前页
        int pageSize = (Integer)map.get("pageSize");  //页数
        int pageStart = (pageCurrent-1)*pageSize;
        map.put("pageStart",pageStart);
        map.put("totalCount",postDao.getSearchPostCount(map));
        List<Post> data = postDao.getSearchDatas(map);
        map.put("searchPosts",data);
        return map;
    }
}
