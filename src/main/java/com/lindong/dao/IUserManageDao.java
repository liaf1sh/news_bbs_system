package com.lindong.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUserManageDao extends IBaseDao{
    /*修改用户状态*/
    Integer updateUserState(@Param("userState")Integer userState, @Param("userId")Integer userId);
}
