package com.lindong.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IPostsManageService extends IBaseService {


    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    boolean deletePosts(@Param("ids") List<Integer> ids);

    /**
     * 修改板块信息
     * @param params
     * @return
     */
    boolean alterPosts(Map<String,Object> params);


}
