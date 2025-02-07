package com.lindong.service;

import com.lindong.domain.Post;

import java.util.List;
import java.util.Map;

public interface ILeadPostService{

    int getCount(Map<String, Object> param);
    /*查看最新热门*/
    List<Post> paging(Map map);
    /*发表帖子*/
    Integer insertNewPost(Post post);

    /**
     * 获取论坛帖子总浏览数
     * @return
     */
    int getBrowseNumber();

}
