package com.lindong.service;

import com.lindong.domain.LeaveWord;
import com.lindong.domain.Post;
import com.lindong.domain.User;

import java.util.List;
import java.util.Map;

public interface IUserInfoService {
    /*查看用户基本信息*/
    Map getUserInfoById(Map map);
    /*个人资料页面使用: 查看指定用户所发的主题*/
    public Map getPostByUserId(Map map);
    /*查看用户的分享数据*/
    public Map getUserPostShare(Map map);
    /*用户留言*/
    Integer insertLeavePost(Map map);
    /*查看用户留言信息*/
    Map getUserLeaveInfo(Map map);
}
