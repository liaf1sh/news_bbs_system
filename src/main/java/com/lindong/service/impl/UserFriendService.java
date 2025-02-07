package com.lindong.service.impl;

import com.lindong.dao.IUserFriendDao;
import com.lindong.domain.FriendGroup;
import com.lindong.domain.User;
import com.lindong.domain.UserFriend;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IUserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserFriendService implements IUserFriendService {

    @Autowired
    private IUserFriendDao userFriendDao;

    @Override
    public List<Object> paging(Map<String, Object> param) {
        return null;
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return 0;
    }

    @Override
    public Object getObjectById(Integer id) {
        return this.userFriendDao.getObjectById(id);
    }

    @Override
    public Object getObjectByName(String name) {
        return null;
    }

    @Override
    public List<Object> findAll(Integer id) {
        return this.userFriendDao.findAll(id);
    }

    @Override
    public List<FriendGroup> getUserFriendGroup(Integer userId) {
        return userFriendDao.getUserFriendGroup(userId);
    }

    @Override
    public UserFriend checkIsFriend(Map map) {
        return userFriendDao.checkIsFriend(map);
    }

    @Override
    public Integer addFriends(Map map) {
        return userFriendDao.addFriends(map);
    }

    @Override
    public Integer deleteFridends(Map map) {
        return userFriendDao.deleteFridends(map);
    }

    @Override
    public boolean checkRequestFriend(Map map) {
        Integer count = userFriendDao.checkRequestFriend(map);
        if (count>0){
            throw new CustomException(ResultCode.FRIEND_APPLY_FILED);
        }
        return true;
    }

    @Transactional(rollbackFor = CustomException.class)
    @Override
    public boolean agreeUserFriend(Map<String,Object> params) {
        int i = userFriendDao.alterUserFriend(params);
        Integer integer = userFriendDao.addFriends(params);
        if (i == 0 || integer == 0){
            throw new CustomException(ResultCode.AGREE_ERROR);
        }
        return true;
    }

    @Override
    public boolean alterUserFriend(Map<String, Object> params) {
        int i = userFriendDao.alterUserFriend(params);
        if (i == 0){
            throw new CustomException(ResultCode.UPDATE_FAILED);
        }
        return true;
    }

}
