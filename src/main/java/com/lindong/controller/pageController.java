package com.lindong.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class pageController {


    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/skipPage/{pageName}")
    public String skipPage(@PathVariable("pageName") String pageName){
        return pageName;
    }

    @RequestMapping("/stair/admin/login")
    public String showLogin(){
        return "forward:/WEB-INF/admin/login.html";
    }

    /*@RequiresRoles("admin")*/
    @RequestMapping(value = "/stair/a/b/admin/{adminPage}")
    public String showAdminPages(@PathVariable("adminPage") String adminPage){
        return "forward:/WEB-INF/admin/"+adminPage+".html";
    }

    @RequestMapping(value = "/404")
    public String page_404(){
        return "forward:/WEB-INF/404.html";
    }

}
