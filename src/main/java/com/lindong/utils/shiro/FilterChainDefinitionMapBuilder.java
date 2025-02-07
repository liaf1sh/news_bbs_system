package com.lindong.utils.shiro;

import java.util.LinkedHashMap;

/**
 * 权限资源初始化类
 */
public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String,String> builderFilterChainDefinitionMap(){

        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        /*map.put("/index","anon");
        map.put("/404","anon");
        map.put("/skipPage/**","anon");
        map.put("/verifyCode","anon");
        map.put("/loginAction","anon");
        map.put("/stair/admin/login","anon");
        map.put("/statics/**","anon");
        map.put("/uploadfiles/**","anon");
        map.put("/bbs/**","anon");
        map.put("/websocket/**","anon");
        map.put("/findUserStatus","anon");*/
        map.put("/doLogout","logout");
        map.put("/stair/**","authc");
        //map.put("/**","authc");
        return map;

    }
}
