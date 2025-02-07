package com.lindong.dao;

import com.lindong.domain.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface INoticeDao extends IBaseDao{
    /*发表公告*/
    Integer publishNotice(Map map);
    /*获取公告数据*/
    List<Notice> getNoticeList(Map map);
    /*删除公告*/
    Integer deleteNoticeById(@Param("ids") List<Integer> ids);
    /*后台编辑公告*/
    Integer updateNoticeById(Map map);
}
