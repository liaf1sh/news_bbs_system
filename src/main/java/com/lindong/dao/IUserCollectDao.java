package com.lindong.dao;

import com.lindong.domain.UserCollect;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface IUserCollectDao extends IBaseDao {

    /**
     * 插入用户收藏数据
     * @param userCollect
     * @return
     */
    int insertUserCollect(UserCollect userCollect);

    /**
     * 查询用户是否在已收藏该主题
     * @param map
     * @return
     */
    int selectCollectCountByMap(Map map);

    /**
     * 批量删除收藏数据
     * @param ids
     * @return
     */
    int deleteCollect(@Param("ids") List<Integer> ids);

}
