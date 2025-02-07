package com.lindong.service;

import java.util.Map;

public interface IPostShareService {

    /**
     * 根据条件查询分享表中是否存在该主题
     * @param params
     * @return
     */
    boolean selectPostShareExist(Map<String,String> params);

    /**
     * 插入帖子分享数据
     * @param map
     * @return
     */
    boolean insertPostShare(Map<String,String> map);

}
