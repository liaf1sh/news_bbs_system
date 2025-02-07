package com.lindong.service;

import com.lindong.domain.Notice;

import java.util.List;
import java.util.Map;

public interface INoticeService extends IBaseService {
    /*发表公告*/
    boolean publishNotice(Map map);
    /*获取公告数据*/
    List<Notice> getNoticeList(Map map);
    /*后台删除公告*/
    boolean deleteNoticeById(List ids);
    /*后台编辑公告*/
    boolean updateNoticeById(Map map);
}
