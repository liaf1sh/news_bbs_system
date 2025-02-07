package com.lindong.service;

import java.util.Map;

public interface IUserManageService  extends IBaseService {
    /*修改用户状态*/
    boolean updateUserState(Integer userState,Integer userId);
}

