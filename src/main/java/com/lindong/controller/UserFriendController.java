package com.lindong.controller;

import com.alibaba.fastjson.JSON;

import com.lindong.domain.FriendGroup;
import com.lindong.domain.UserFriend;
import com.lindong.exception.ApiResult;
import com.lindong.exception.ResultCode;
import com.lindong.service.IUserFriendService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bbs/userFriend")
public class UserFriendController {

    @Resource
    private IUserFriendService userFriendService;

    @RequestMapping("/listFriend")
    @ResponseBody
    public List<UserFriend> listFriend(int id){
        System.out.println("=========="+id);
        List<UserFriend> lists = (List)userFriendService.findAll(id);
       /* String json = JSON.toJSONString(lists);
        System.out.println("=====打印======");
        System.out.println(json);*/
        return lists;
    }

    @RequestMapping("/listFriendGroup")
    @ResponseBody
    public List<FriendGroup> listFriendGroup(@RequestParam("userId") Integer userId){
        System.out.println("=========="+userId);
        List<FriendGroup> lists = userFriendService.getUserFriendGroup(userId);
        return lists;
    }

    @RequestMapping("/checkIsFriend")
    @ResponseBody
    public UserFriend checkIsFriend(@RequestBody Map map){
        System.out.println("我的Id"+map.get("userId")+"好友id:"+map.get("friendId"));
        UserFriend lists = userFriendService.checkIsFriend(map);
        return lists;
    }

    @RequestMapping("/addFriends")
    @ResponseBody
    public Integer addFriends(@RequestBody Map map){
        Integer count  =userFriendService.addFriends(map);
        return count;

    }

    @RequestMapping("/deleteFriends")
    @ResponseBody
    public Integer deleteFriends(@RequestBody Map map){
        System.out.println(JSON.toJSONString(map));
        Integer count  =userFriendService.deleteFridends(map);
        return count;

    }
    @RequestMapping("/checkRequestFriend")
    @ResponseBody
    public ApiResult checkRequestFriend(@RequestBody Map map){
        userFriendService.checkRequestFriend(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "consentApply",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult consentApply(@RequestBody Map params){
        userFriendService.agreeUserFriend(params);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "refuseApply",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult refuseApply(@RequestBody Map params){
        userFriendService.alterUserFriend(params);
        return ApiResult.of(ResultCode.SUCCESS);
    }

}
