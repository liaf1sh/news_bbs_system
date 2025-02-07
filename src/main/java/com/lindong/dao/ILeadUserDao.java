package com.lindong.dao;

import com.lindong.domain.User;

public interface ILeadUserDao{
    /*查看指定用户信息*/
    public User getUserInfoById(Integer userId);
}
