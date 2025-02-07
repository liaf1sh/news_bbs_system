package com.lindong.service.impl;

import com.lindong.dao.INoticeDao;
import com.lindong.domain.Notice;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
public class NoticeService implements INoticeService {
    @Autowired
    private INoticeDao noticeDao;

    @Override
    public boolean publishNotice(Map map) {
        Integer i = noticeDao.publishNotice(map);
        if (i == 0){
            throw new CustomException(ResultCode.DATA_ERROR);
        }
        return true;
    }

    @Override
    public List<Notice> getNoticeList(Map map) {
        int pageCurrent = (Integer)map.get("pageCurrent"); //当前页
        int pageSize = (Integer)map.get("pageSize");  //页数
        int pageStart = (pageCurrent-1)*pageSize;
        map.put("pageStart",pageStart);
        return noticeDao.getNoticeList(map);
    }

    @Override
    public boolean deleteNoticeById(List ids) {
        Integer i =  noticeDao.deleteNoticeById(ids);
        if (i == 0){
            throw new CustomException(ResultCode.DELETE_ERROR);
        }
        return true;
    }

    @Override
    public boolean updateNoticeById(Map map) {
        Integer i =  noticeDao.updateNoticeById(map);
        if (i == 0){
            throw new CustomException(ResultCode.UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public List<Object> paging(Map<String, Object> param) {
        return null;
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return noticeDao.getCount(param);
    }

    @Override
    public Object getObjectById(Integer id) {
        return null;
    }

    @Override
    public Object getObjectByName(String name) {
        return null;
    }
}
