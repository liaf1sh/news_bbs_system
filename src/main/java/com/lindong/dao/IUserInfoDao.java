package com.lindong.dao;

import com.lindong.domain.LeaveWord;
import com.lindong.domain.Post;
import com.lindong.domain.User;

import java.util.List;
import java.util.Map;

public interface IUserInfoDao {
    /*查看用户基本信息*/
    User getBasicUserInfoById(Integer userId);
    /* 查看好友数量*/
    Integer getUserFriendsCount(Integer userId);
    /*查看用户所发主题数量*/
    Integer getUserPostCount(Integer userId);
    /*获取用户回复主题数量*/
    Integer getUserPostDetailCount(Integer userId);
    /*获取用户日志数量*/
    Integer getUserLogCount(Integer userId);
    /*查看用户最后发表主题时间*/
    Post getLastPostTime(Integer userId);
    /*查看用户分享数据*/
    List<Post> getUserPostShare(Map map);
    /*查看用户分享数据总条数*/
    Integer getCount(Map map);
    /*用户留言*/
    Integer insertLeavePost(Map map);
    /*查看用户留言信息*/
    List<LeaveWord> getUserLeaveInfo(Map map);
}
