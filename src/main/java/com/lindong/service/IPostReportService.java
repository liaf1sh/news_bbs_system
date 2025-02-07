package com.lindong.service;

import com.lindong.domain.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IPostReportService extends IBaseService{

    /**
     * 查询用户对该主题或帖子存在举报数据
     * @param params
     * @return
     */
    boolean selectPostReport(Map<String,Integer> params);

    /**
     * 插入举报数据
     * @param map
     * @return
     */
    boolean insertPostReport(Map<String,String> map);


    /**
     * 批量删除举报数据
     * @param ids
     * @return
     */
    boolean deletePostReport(@Param("ids") List<Integer> ids);


    Post getPost(Integer id);

    /**
     * 插入多条系统提醒数据
     * @param params
     * @return
     */
    boolean disposeReport(Map<String,Object> params);

}
