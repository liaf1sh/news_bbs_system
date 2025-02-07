package com.lindong.controller;


import com.lindong.utils.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("bbs/testRedis")
public class TestController {

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("/test")
    @ResponseBody
    public String testRedis(){
        if (!redisUtil.hasKey("test:01")){
            redisUtil.set("test:01","这是一个测试redis缓存");
        }
        System.out.println(redisUtil.get("test:01"));
        return "success!";
    }
}
