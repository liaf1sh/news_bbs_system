package com.lindong.controller;

import com.lindong.service.IUserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;
@Controller
@RequestMapping("/bbs/userInfo")
public class UserInfoController {
    @Resource
    private IUserInfoService userInfoService;

    @RequestMapping("/getUserInfoById")
    @ResponseBody
    public Map getUserInfoById(@RequestBody Map map){
        map= userInfoService.getUserInfoById(map);
        return map;
    }

    @RequestMapping("/getPostByUserId")
    @ResponseBody
    public Map getPostByUserId(@RequestBody Map map){
        return  userInfoService.getPostByUserId(map);
    }

    @RequestMapping("/getUserPostShare")
    @ResponseBody
    public Map getUserPostShare(@RequestBody Map map){
        map =userInfoService.getUserPostShare(map);
        return map;
    }

    @RequestMapping("/insertLeavePost")
    @ResponseBody
    public Integer insertLeavePost(@RequestBody Map map){
        Integer count =userInfoService.insertLeavePost(map);
        System.out.println(map.get("leaveUserId")+" "+map.get("leaveName")+" "+map.get("leavePhoto")+" "+map.get("leaveContent")+" "+map.get("toUserId"));
        return count;
    }
    @RequestMapping("/getUserLeaveInfo")
    @ResponseBody
    public Map getUserLeaveInfo(@RequestBody Map map){
        //Integer userId = Integer.parseInt(String.valueOf(map.get("userId")));
        return  userInfoService.getUserLeaveInfo(map);
    }
}
