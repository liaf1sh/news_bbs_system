package com.lindong.controller;

import com.lindong.domain.Plate;
import com.lindong.domain.User;
import com.lindong.exception.ApiResult;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.ILeadPostService;
import com.lindong.service.IPlateService;
import com.lindong.service.IUserService;
import com.lindong.utils.shiro.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("stair/datum")
public class AdminDatumController {

    @Resource
    private IUserService userService;
    @Resource
    private IPlateService plateService;
    @Resource
    private ILeadPostService iLeadPostService;

    @RequestMapping("getUserInfo")
    @ResponseBody
    public User getUserInfo(String username){
        return userService.findByName(username);
    }

    @RequestMapping("alterUser")
    @ResponseBody
    public ApiResult alterUser(@RequestBody Map map){
        String old_password = (String) map.get("old_password");
        if (old_password != null){      //密码更新
            User user = userService.getPwdSalt((String) map.get("username"));
            String oldPwd = MD5.getMd5String(old_password,user.getSalt());
            if (!oldPwd.equals(user.getPassword())){   //与旧密码验证
                throw new CustomException(ResultCode.PASSWORD_FAILED);
            }
            String new_salt = MD5.getSalt();
            map.put("salt",new_salt);
            String DBPassword = MD5.getMd5String((String) map.get("new_password"),new_salt);
            map.put("password",DBPassword);
            userService.updateUser(map);
        }else {         //基本资料更新
            userService.updateUser(map);
        }
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping("getCount")
    @ResponseBody
    public Map getCount(){
        Map<String,Integer> map = new HashMap<>();
        int userCount = userService.getUserCount(); //论坛用户总记录数
        int browseNumber = iLeadPostService.getBrowseNumber();
        int themeCount = 0;     //论坛主题总记录数
        int postsCount = 0;     //论坛帖子总记录数
        List<Plate> plates = plateService.findAll();
        for (Plate plate : plates) {
            themeCount = themeCount + plate.getTheme();
            postsCount = postsCount + plate.getPosts();
        }
        map.put("userCount",userCount);
        map.put("browseNumber",browseNumber);
        map.put("themeCount",themeCount);
        map.put("postsCount",postsCount);
        return map;
    }

}
