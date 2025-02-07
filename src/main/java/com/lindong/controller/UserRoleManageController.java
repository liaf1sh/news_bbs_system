package com.lindong.controller;

import com.lindong.aspect.Log;
import com.lindong.domain.Role;
import com.lindong.exception.ApiResult;
import com.lindong.exception.ResultCode;
import com.lindong.service.IRoleService;
import com.lindong.service.IUserRoleService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("stair/userRoleManage")
public class UserRoleManageController {

    @Resource
    private IUserRoleService userRoleService;

    @RequestMapping(value = "/listRecords",method = RequestMethod.POST)
    @ResponseBody
    public Map listRecords(@RequestBody Map params){
        Integer count = userRoleService.getCount(params);
        List<Role> data = userRoleService.paging(params);
        Map map = new HashMap();
        map.put("count",count);
        map.put("data",data);
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @RequestMapping(value = "/insertUserRoles",method = RequestMethod.POST)
    @RequiresRoles("admin")
    @Log(operation = "添加用户角色")
    @ResponseBody
    public ApiResult insertUserRoles(@RequestBody Map<String,Integer> map){
        userRoleService.insertUserRole(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/alterUserRoles",method = RequestMethod.POST)
    @RequiresRoles("admin")
    @Log(operation = "修改用户角色信息")
    @ResponseBody
    public ApiResult alterUserRoles(@RequestBody Map<String,Integer> map){
        userRoleService.alterUserRole(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/deleteUserRoles",method = RequestMethod.POST)
    @RequiresRoles("admin")
    @Log(operation = "删除用户角色")
    @ResponseBody
    public ApiResult deleteUserRoles(@RequestBody List<Integer> ids){
        userRoleService.deleteUserRole(ids);
        return ApiResult.of(ResultCode.SUCCESS);
    }



}
