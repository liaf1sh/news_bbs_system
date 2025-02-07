package com.lindong.service;

import com.lindong.domain.Post;

import java.util.List;
import java.util.Map;

public interface IAttentionService extends IBaseService {

    /**
     * 删除关注数据
     * @param uid
     * @param aid
     * @return
     */
    boolean deleteAttention(Integer uid,Integer aid);

    /**
     * 查询用户关注人的最新帖子
     * @param uid
     * @return
     */
    List<Post> getCarePosts(Integer uid);

    /**
     * 查询该用户是否已关注
     * @param map
     * @return
     */
    boolean selectCare(Map<String,Integer> map);
}
