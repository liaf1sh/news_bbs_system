package com.lindong.dao;

import com.lindong.domain.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ILeadPostDao{
    /*查看主题*/
    List<Post> paging(Map map);
    /*查看数据总数*/
    int getCount(Map<String, Object> param);
    /*发表帖子*/
    Integer insertNewPost(Post post);
    /*个人资料页面使用: 查看指定用户所发的主题*/
    List<Post> getPostByUserId(Map map);

    /*发帖更新用户经验*/
    Integer updateExperience(@Param("userId") Integer userId, @Param("experience") Integer experience );
    /*计算用户一天发了多少个主题*/
    Integer getTodayNewPostCount(@Param("userId") Integer userId);

    /**
     * 获取论坛帖子总浏览数
     * @return
     */
    int getBrowseNumber();
}
