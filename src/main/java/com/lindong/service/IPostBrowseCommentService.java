package com.lindong.service;

import com.lindong.domain.Grade;
import com.lindong.domain.Post;
import com.lindong.domain.PostDetails;

import java.util.List;
import java.util.Map;

public interface IPostBrowseCommentService extends IBaseService {

    /**
     * 根据条件筛选出最新前8条指定类型主题
     * @param params
     * @return
     */
    List<Post> selectNewestPosts(Map<String,String> params);

    /**
     * 根据用户id获取主题数
     * @param uid
     * @return
     */
    int getThemeCountById(Integer uid);

    /**
     * 根据用户id获取帖子数
     * @param uid
     * @return
     */
    int getPostsCountById(Integer uid);

    /**
     * 根据用户id获取用户总经验
     * @param uid
     * @return
     */
    int getExperienceById(Integer uid);

    /**
     * 根据用户经验获取用户等级
     * @param experience
     * @return
     */
    Grade getGrade(Integer experience);

    /**
     * 增加帖子回复数据
     * @param postDetails
     * @return
     */
    boolean insertPostDetails(PostDetails postDetails);

    /**
     * 更新主题数据
     * @param params
     * @return
     */
    boolean updatePost(Map<String,String> params);

    /**
     * 查询用户对该主题是否有操作记录
     * @param map
     * @return
     */
    boolean selectThemeOperation(Map<String,Integer> map);

    /**
     * 插入用户对主题已操作记录
     * @param map
     * @return
     */
    boolean insertThemeOperation(Map<String,Integer> map);

    /**
     * 查询用户关注记录
     * @param uid
     * @param aid
     * @return
     */
    boolean selectAttention(Integer uid,Integer aid);

    /**
     * 用户关注
     * @param uid
     * @param aid
     * @return
     */
    boolean insertAttention(Integer uid,Integer aid);

    /**
     * 点击主题,给主题的浏览次数加一
     * @param
     * @return
     */
    Integer clickAddBrowseCount(Integer id);

    /*搜索*/
    Map getSearchPosts(Map map);
}
