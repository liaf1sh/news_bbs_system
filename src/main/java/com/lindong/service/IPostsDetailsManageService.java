package com.lindong.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPostsDetailsManageService extends IBaseService {

    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    boolean deletePostsDetails(@Param("ids") List<Integer> ids);

}
