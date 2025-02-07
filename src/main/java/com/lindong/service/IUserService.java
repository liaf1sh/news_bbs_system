package com.lindong.service;

import com.lindong.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface IUserService {

    /**
     * 根据用户名获取用户登录所需信息
     * @param username
     * @return
     */
    User getPwdSalt(String username);

    /**
     * 用户登录
     * @param param
     * @return
     */
    boolean login(Map<String,String> param);

    /**
     * 用户更新
     * @param params
     * @return
     */
    boolean updateUser(Map<String,String> params);

    /**
     * 根据用户账号获取用户信息
     * @param username
     * @return
     */
    User findByName(String username);

    /**
     * 根据用户id集合获取用户信息
     * @param ids
     * @return
     */
    List<User> listUser(List<Integer> ids);

    /**
     * 用户注册
     * @param params
     * @return
     */
    boolean register(Map<String,String> params);

    /**
     * 获取论坛用户总记录数据
     * @return
     */
    int getUserCount();

}
