package com.lindong.controller;

import com.lindong.aspect.Log;
import com.lindong.domain.Role;
import com.lindong.exception.ApiResult;
import com.lindong.exception.ResultCode;
import com.lindong.service.IRoleService;
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
@RequestMapping("stair/roleManage")
public class RoleManageController {

    @Resource
    private IRoleService roleService;

    @RequestMapping(value = "/insertRoles",method = RequestMethod.POST)
    @RequiresRoles("admin")
    @Log(operation = "添加角色")
    @ResponseBody
    public ApiResult insertRoles(@RequestBody Map<String,String> map){
        roleService.insertRole(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/alterRoles",method = RequestMethod.POST)
    @Log(operation = "修改角色信息")
    @RequiresRoles("admin")
    @ResponseBody
    public ApiResult alterRoles(@RequestBody Role role){
        roleService.alterRole(role);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/deleteRoles",method = RequestMethod.POST)
    @RequiresRoles("admin")
    @Log(operation = "删除角色")
    @ResponseBody
    public ApiResult deleteRoles(@RequestBody List<Integer> ids){
        roleService.deleteRole(ids);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/listRole")
    @ResponseBody
    public List<Role> listRole(){
        Map<String,Object> map = new HashMap<>();
        return (List)roleService.paging(map);
    }

    @RequestMapping(value = "/listRoleRecords",method = RequestMethod.POST)
    @ResponseBody
    public Map listRoleRecords(@RequestBody Map<String,Object> params){
        Integer total = roleService.getCount(params);
        List<Role> data = (List)roleService.paging(params);
        Map map = new HashMap();
        map.put("code",0);
        map.put("msg","");
        map.put("count",total);
        map.put("data",data);
        return map;
    }

}
