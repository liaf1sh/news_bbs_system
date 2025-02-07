package com.lindong.dao;

import com.lindong.domain.FriendGroup;
import com.lindong.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUserDao {

    /**
     * 根据用户名称获取用户信息
     * @param username
     * @return
     */
    User getPwdSalt(String username);

    /**
     * 用户更新
     * @param params
     * @return
     */
    int updateUser(Map<String,String> params);

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
    List<User> listUser(@Param("ids") List<Integer> ids);


    /**
     * 增加一条用户数据
     * @param map
     * @return
     */
    int addUser(Map<String,String> map);

    /**
     * 绑定用户积分数据
     * @param user_id
     * @return
     */
    int addIntegral(Integer user_id);

    /**
     * 绑定用户经验数据
     * @param user_id
     * @return
     */
    int addExperience(Integer user_id);

    /**
     * 绑定用户签到数据
     * @param user_id
     * @return
     */
    int addSign(Integer user_id);

    /**
     * 插入注册用户默认分组数据
     * @param list
     * @return
     */
    int insertFriendGroup(@Param("list") List<FriendGroup> list);

    /**
     * 获取论坛用户总记录数据
     * @return
     */
    int getUserCount();

}
