package com.lindong.service.impl;

import com.lindong.dao.ILeadPostDao;
import com.lindong.dao.IPostBrowseCommentDao;
import com.lindong.dao.IUserInfoDao;
import com.lindong.domain.Grade;
import com.lindong.domain.LeaveWord;
import com.lindong.domain.Post;
import com.lindong.domain.User;
import com.lindong.service.IPostBrowseCommentService;
import com.lindong.service.IUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
public class UserInfoService implements IUserInfoService {
    @Resource
    private IUserInfoDao userInfoDao;
    @Resource
    private IPostBrowseCommentDao postBrowseCommentDao;
    @Resource
    private ILeadPostDao leadPostDao;
    @Override
    public Map getUserInfoById(Map map) {
        int userId = Integer.parseInt(String.valueOf(map.get("userId"))); //用户id
        /*获取用户id基本信息和积分信息*/
        User user = userInfoDao.getBasicUserInfoById(userId);
        map.put("basicUserInfo",user);
        /*根据用户id获取用户总经验*/
        Integer experience =postBrowseCommentDao.getExperienceById(userId);
        /*通过用户总经验获取用户等级信息*/
        Grade grade = postBrowseCommentDao.getGrade(experience);
        map.put("grade",grade);
        /* 查看好友数量*/
        Integer userFriendsCount = userInfoDao.getUserFriendsCount(userId);
        map.put("userFriendsCount",userFriendsCount);
        /*查看用户所发主题数量*/
        Integer userPostCount  =userInfoDao.getUserPostCount(userId);
        map.put("userPostCount",userPostCount);
        /*获取用户回复主题数量*/
        Integer userPostDetailCount =userInfoDao.getUserPostDetailCount(userId);
        map.put("userPostDetailCount",userPostDetailCount);
        /*获取用户日志数量*/
        Integer userLogCount =userInfoDao.getUserLogCount(userId);
        map.put("userLogCount",userLogCount);
        /*查看用户最后发表主题时间*/
        Post postTime = userInfoDao.getLastPostTime(userId);
        map.put("lastPostTime",postTime);
        return map;
    }

    @Override
    public Map getPostByUserId(Map map) {
        int pageCurrent = (Integer)map.get("currentPage"); //当前页
        int pageSize = (Integer)map.get("pageSize");  //页数
        int pageStart = (pageCurrent-1)*pageSize;
        map.put("pageStart",pageStart);
        List<Post> list = leadPostDao.getPostByUserId(map);
        map.put("postList",list);
        map.put("chooseSelect","MyPost");
        Integer dataCount = leadPostDao.getCount(map);
        map.put("dataCount",dataCount);
        return map;
    }

    @Override
    public Map getUserPostShare(Map map) {
        int pageCurrent = (Integer)map.get("currentPage"); //当前页
        int pageSize = (Integer)map.get("pageSize");  //页数
        int pageStart = (pageCurrent-1)*pageSize;
        map.put("pageStart",pageStart);/*Integer.parseInt(String.valueOf(map.get("userId")))*/
        map.put("Type","share");
        Integer shareTotal = userInfoDao.getCount(map);
        System.out.println("我有:"+shareTotal);
        map.put("shareTotal",shareTotal);
        map.put("sharePostList", userInfoDao.getUserPostShare(map)); //用户分享的分享数据
        return map;
    }

    @Override
    public Integer insertLeavePost(Map map) {
        return userInfoDao.insertLeavePost(map);
    }

    @Override
    public Map getUserLeaveInfo(Map map) {
        int pageCurrent = (Integer)map.get("currentPage"); //当前页
        int pageSize = (Integer)map.get("pageSize");  //页数
        int pageStart = (pageCurrent-1)*pageSize;
        map.put("pageStart",pageStart);
        map.put("Type","leave");
        Integer leaveTotal = userInfoDao.getCount(map);
        map.put("leaveTotal",leaveTotal);
        map.put("leaveList",userInfoDao.getUserLeaveInfo(map));
        return map;
    }
}
