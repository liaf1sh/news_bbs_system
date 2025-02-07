package com.lindong.controller;

import com.alibaba.fastjson.JSON;
import com.lindong.aspect.Log;
import com.lindong.domain.User;
import com.lindong.exception.ApiResult;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IUserManageService;
import com.lindong.service.impl.UserService;
import com.lindong.utils.IPUtils;
import com.lindong.utils.shiro.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("stair/userManage")
public class UserManageController {
    @Resource
    private IUserManageService userManageService;
    @Resource
    private UserService userService;
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Map getUserInfo(@RequestBody Map param){
        List<Object>  userList = userManageService.paging(param);
        Integer dataTotal = userManageService.getCount(param);
        param.put("data",userList);
        param.put("code",0);
        param.put("count",dataTotal);
        param.put("msg","");
        return param;
    }
    @RequestMapping("/updateUserState")
    @Log(operation = "用户信息修改")
    @ResponseBody
    public ApiResult updateUserState(@RequestParam("userState") Integer userState, @RequestParam("userId") Integer userId) {
        userManageService.updateUserState(userState,userId);
        return ApiResult.of(ResultCode.SUCCESS);
    }
    @RequestMapping("/addUser")
    @Log(operation = "添加用户")
    @ResponseBody
    public ApiResult addUser(@RequestBody Map map, HttpServletRequest request) {
        User byNameUser = userService.findByName((String)map.get("username"));
        if (byNameUser != null){
            throw new CustomException(ResultCode.USER_EXIST);
        }
        String ip = IPUtils.getIpAddr(request);
        System.out.println(ip);
        String salt = MD5.getSalt();
        String passwordDB = MD5.getMd5String((String)map.get("password"),salt);
        map.put("password",passwordDB);
        map.put("salt",salt);
        map.put("register_ip",ip);
        userService.register(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping("/resetPassword")
    @ResponseBody
    public ApiResult addUser(@RequestParam("id") Integer id) {
        Map map = new HashMap();
        String salt = MD5.getSalt();
        String passwordDB = MD5.getMd5String("000000",salt);
        map.put("id",id);
        map.put("password",passwordDB);
        map.put("salt",salt);
        userService.updateUser(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }
}
