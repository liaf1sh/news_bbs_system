package com.lindong.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPostsDetailsManageDao extends IBaseDao {

    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    int deletePostsDetails(@Param("ids") List<Integer> ids);

}
