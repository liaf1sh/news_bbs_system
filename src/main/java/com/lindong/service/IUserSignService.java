package com.lindong.service;

import com.lindong.domain.Integral;
import com.lindong.domain.UserSign;

import java.util.List;
import java.util.Map;

public interface IUserSignService extends IBaseService {

    /**
     * 用户签到
     * @param userSign
     * @return
     */
    boolean updateUserSign(UserSign userSign);

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
    boolean updateUserIntegral(Integral integral);

    /**
     * 获取今日与昨天发帖数
     * @return
     */
    List<Map> selectPostCountByDate();

    /**
     * 获取论坛总贴数
     * @return
     */
    int selectPostCount();

    /**
     * 获取论坛会员数
     * @return
     */
    int selectUserCount();

    /**
     * 查询今日签到人数
     * @return
     */
    int selectTodaySignCount();

}
