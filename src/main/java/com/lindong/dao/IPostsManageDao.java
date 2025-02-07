package com.lindong.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IPostsManageDao extends IBaseDao{

    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    int deletePosts(@Param("ids") List<Integer> ids);

    /**
     * 修改板块信息
     * @param params
     * @return
     */
    int alterPosts(Map<String,Object> params);

}
