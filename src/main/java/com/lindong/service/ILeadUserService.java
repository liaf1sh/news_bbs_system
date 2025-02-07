package com.lindong.service;

import com.lindong.domain.User;


public interface ILeadUserService {
    /*通过用户id查找用户信息*/
    public User getUserInfoById(Integer userId);
}
