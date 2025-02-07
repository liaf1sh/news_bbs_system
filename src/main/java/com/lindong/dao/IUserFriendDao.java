package com.lindong.dao;


import com.lindong.domain.FriendGroup;
import com.lindong.domain.UserFriend;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUserFriendDao extends IBaseDao {

    /**
     * 根据用户id查询所有的好友以及分组列表
     * @param id
     * @return
     */
    List<Object> findAll(@Param("id") Integer id);

    /*查看用户好友分组*/
    List<FriendGroup> getUserFriendGroup(Integer userId);
    /*检查是否是好友*/
    UserFriend checkIsFriend(Map map);
    /*添加好友*/
    Integer addFriends(Map map);
    /*删除好友*/
    Integer deleteFridends(Map map);
    /*检查是否有此好友请求*/
    Integer checkRequestFriend(Map map);

    /**
     * 更新好友状态
     * @param params
     * @return
     */
    int alterUserFriend(Map<String,Object> params);

}
