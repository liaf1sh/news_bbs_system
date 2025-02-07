package com.lindong.dao;

import com.lindong.domain.Grade;
import com.lindong.domain.Post;
import com.lindong.domain.PostDetails;
import com.lindong.domain.PostOperation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IPostBrowseCommentDao extends IBaseDao{

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
     * 点击主题,给主题的浏览次数加一
     * @param
     * @return
     */
    Integer clickAddBrowseCount(Integer id);


    /**
     * 插入帖子回复数据
     * @param postDetails
     * @return
     */
    int insertPostDetails(PostDetails postDetails);

    /**
     * 更新主题数据
     * @param params
     * @return
     */
    int updatePost(Map<String,String> params);

    /**
     * 查询用户对该主题是否有操作记录
     * @param map
     * @return
     */
    int selectThemeOperation(Map<String,Integer> map);

    /**
     * 插入用户对主题已操作记录
     * @param map
     * @return
     */
    int insertThemeOperation(Map<String,Integer> map);

    /**
     * 查询用户关注记录
     * @param uid
     * @param aid
     * @return
     */
    int selectAttention(@Param("uid") Integer uid,@Param("aid") Integer aid);

    /**
     * 插入关注数据
     * @param uid
     * @param aid
     * @return
     */
    int insertAttention(@Param("uid") Integer uid,@Param("aid") Integer aid);

    /*搜索*/
    List<Post> getSearchDatas(Map map);
    /*搜索数据总数*/
    Integer getSearchPostCount(Map map);

}
