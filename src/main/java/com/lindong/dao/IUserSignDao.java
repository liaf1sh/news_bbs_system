package com.lindong.dao;

import com.lindong.domain.Integral;
import com.lindong.domain.UserSign;

import java.util.List;
import java.util.Map;

public interface IUserSignDao extends IBaseDao {

    /**
     * 修改用户签到数据
     * @param userSign
     * @return
     */
    int updateUserSign(UserSign userSign);

    /**
     * 根据用户id获取积分数据
     * @param uid
     * @return
     */
    Integral selectIntegral(Integer uid);

    /**
     * 更新积分数据
     * @param integral
     * @return
     */
    int updateUserIntegral(Integral integral);

    /**
     * 查询今日与昨天发帖数
     * @return
     */
    List<Map> selectPostCountByDate();

    /**
     * 查询论坛总贴数
     * @return
     */
    int selectPostCount();

    /**
     * 查询论坛会员数
     * @return
     */
    int selectUserCount();

    /**
     * 查询今日签到人数
     * @return
     */
    int selectTodaySignCount();

}
