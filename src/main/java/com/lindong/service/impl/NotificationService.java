package com.lindong.service.impl;

import com.lindong.dao.INotificationDao;
import com.lindong.domain.Post;
import com.lindong.domain.SysWarn;
import com.lindong.domain.UserFriend;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private INotificationDao notificationDao;

    @Override
    public boolean alterPostDetails(List<Integer> ids) {
        int i = notificationDao.alterPostDetails(ids);
        if (i == 0){
            throw new CustomException(ResultCode.DATA_ERROR);
        }
        return true;
    }

    @Override
    public List<UserFriend> selectFriendApply(Map<String,Integer> map) {
        Integer pageNo = map.get("pageNo");
        Integer pageSize = map.get("pageSize");
        Integer index = (pageNo - 1) * pageSize;
        map.put("index",index);
        List<UserFriend> userFriends = notificationDao.selectFriendApply(map);
        return userFriends;
    }

    @Override
    public int getApplyCount(Map<String,Integer> map) {
        return notificationDao.getApplyCount(map);
    }

    @Override
    public boolean alterUserFriend(List<Integer> ids) {
        int i = notificationDao.alterUserFriend(ids);
        if (i == 0){
            throw new CustomException(ResultCode.DELETE_ERROR);
        }
        return true;
    }

    @Override
    public boolean insertUserFriend(UserFriend userFriend) {
        int i = notificationDao.insertUserFriend(userFriend);
        if (i == 0){
            throw new CustomException(ResultCode.AGREE_ERROR);
        }
        return true;
    }

    @Override
    public List<SysWarn> listSysWarn(Map<String, Object> params) {
        Integer pageNo = (Integer)params.get("pageNo");
        Integer pageSize = (Integer)params.get("pageSize");
        Integer index = (pageNo - 1) * pageSize;
        params.put("index",index);
        List<SysWarn> data = notificationDao.listSysWarn(params);
        return data;
    }

    @Override
    public int getCountSysWarn(Map<String, Object> params) {
        return notificationDao.getCountSysWarn(params);
    }

    @Override
    public boolean deleteSysWarnByList(List<Integer> ids) {
        int i = notificationDao.deleteSysWarnByList(ids);
        if (i == 0){
            throw new CustomException(ResultCode.DELETE_ERROR);
        }
        return true;
    }

    @Override
    public List<Object> paging(Map<String, Object> param) {
        int pageSize = (Integer)param.get("pageSize");
        int pageNo = (Integer)param.get("pageNo");
        int index = (pageNo - 1) * pageSize;
        param.put("index",index);
        List<Object> objects = notificationDao.paging(param);
        return objects;
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return notificationDao.getCount(param);
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
