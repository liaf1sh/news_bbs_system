package com.lindong.dao;


import com.lindong.domain.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IPostReportDao extends IBaseDao{

    /**
     * 查询用户对该主题或帖子存在举报数据
     * @param params
     * @return
     */
    int selectPostReport(Map<String,Integer> params);

    /**
     * 插入举报数据
     * @param map
     * @return
     */
    int insertPostReport(Map<String,String> map);

    /**
     * 批量删除举报数据
     * @param ids
     * @return
     */
    int deletePostReport(@Param("ids") List<Integer> ids);

    /**
     * 修改举报数据处理状态
     * @param id
     * @return
     */
    int alterPostReport(Integer id);

    Post getPost(Integer id);

    /**
     * 插入多条系统提醒数据
     * @param params
     * @return
     */
    int insertWarnList(Map<String,Object> params);

}
