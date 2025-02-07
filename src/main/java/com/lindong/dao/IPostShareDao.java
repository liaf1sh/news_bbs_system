package com.lindong.dao;

import java.util.Map;

public interface IPostShareDao {

    /**
     * 根据条件查询分享表中是否存在该主题
     * @param params
     * @return
     */
    int selectPostShareExist(Map<String,String> params);

    /**
     * 插入帖子分享数据
     * @param map
     * @return
     */
    int insertPostShare(Map<String,String> map);
}
