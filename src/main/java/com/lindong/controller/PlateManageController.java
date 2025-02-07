package com.lindong.controller;

import com.lindong.aspect.Log;
import com.lindong.domain.Plate;
import com.lindong.exception.ApiResult;
import com.lindong.exception.ResultCode;
import com.lindong.service.IPlateManageService;
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
@RequestMapping("stair/plateManage")
public class PlateManageController {

    @Resource
    private IPlateManageService plateManageService;

    @RequestMapping(value = "/listRecords",method = RequestMethod.POST)
    @ResponseBody
    public Map listRecords(@RequestBody Map params){
        int count = plateManageService.getCount(params);
        List<Plate> data = plateManageService.paging(params);
        Map resultMap = new HashMap();
        resultMap.put("count",count);
        resultMap.put("data",data);
        resultMap.put("code",0);
        resultMap.put("msg","");
        return resultMap;
    }

    @RequestMapping(value = "/removePlate",method = RequestMethod.POST)
    @Log(operation = "删除版块")
    @RequiresRoles("admin")
    @ResponseBody
    public ApiResult removePlate(@RequestBody List<Integer> ids){
        plateManageService.deletePlate(ids);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/alterPlate",method = RequestMethod.POST)
    @Log(operation = "修改板块信息")
    @RequiresRoles("admin")
    @ResponseBody
    public ApiResult alterPlate(@RequestBody Map map){
        plateManageService.alterPlate(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/addPlate",method = RequestMethod.POST)
    @RequiresRoles("admin")
    @ResponseBody
    public ApiResult addPlate(@RequestBody Plate plate){
        plateManageService.addPlate(plate);
        return ApiResult.of(ResultCode.SUCCESS);
    }

}
