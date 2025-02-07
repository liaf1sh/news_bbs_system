package com.lindong.service.impl;

import com.lindong.dao.ILeadUserDao;
import com.lindong.domain.User;
import com.lindong.service.ILeadUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LeadUserService implements ILeadUserService {
    @Resource
    private ILeadUserDao iLeadUserDao;
    /*根据用户id查看用户信息*/
    @Override
    public User getUserInfoById(Integer userId) {
        return iLeadUserDao.getUserInfoById(userId);
    }

}
