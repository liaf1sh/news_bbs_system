package com.lindong.dao;

import com.lindong.domain.SysWarn;
import com.lindong.domain.UserFriend;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface INotificationDao extends IBaseDao {

    /**
     * 修改帖子回复查看状态
     * @param ids
     * @return
     */
    int alterPostDetails(@Param("ids") List<Integer> ids);

    /**
     * 查询好友申请记录
     * @param map
     * @return
     */
    List<UserFriend> selectFriendApply(Map<String,Integer> map);

    int getApplyCount(Map<String,Integer> map);

    /**
     * 修改好友表状态
     * @param ids
     * @return
     */
    int alterUserFriend(@Param("ids") List<Integer> ids);

    /**
     * 插入好友同意数据
     * @param userFriend
     * @return
     */
    int insertUserFriend(UserFriend userFriend);

    /**
     * 获取指定用户的系统消息
     * @param params
     * @return
     */
    List<SysWarn> listSysWarn(Map<String,Object> params);

    int getCountSysWarn(Map<String,Object> params);

    /**
     * 批量删除系统提醒通知
     * @param ids
     * @return
     */
    int deleteSysWarnByList(@Param("ids") List<Integer> ids);

}
