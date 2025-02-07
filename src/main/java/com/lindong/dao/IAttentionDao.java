package com.lindong.dao;

import com.lindong.domain.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IAttentionDao extends IBaseDao {

    /**
     * 删除关注数据
     * @param uid
     * @param aid
     * @return
     */
    int deleteAttention(@Param("uid") Integer uid,@Param("aid") Integer aid);

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
    int selectCare(Map<String,Integer> map);
}
