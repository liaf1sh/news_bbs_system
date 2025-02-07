package com.lindong.controller;

import com.lindong.domain.PostReport;
import com.lindong.domain.SysLog;
import com.lindong.exception.ApiResult;
import com.lindong.exception.ResultCode;
import com.lindong.service.ISysLogService;
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
@RequestMapping("stair/sysLog")
public class SysLogController {

    @Resource
    private ISysLogService sysLogService;
    @RequestMapping(value = "/listRecords",method = RequestMethod.POST)
    @ResponseBody
    public Map listRecords(@RequestBody Map params){
        Integer count = sysLogService.getCount(params);
        List<SysLog> data = sysLogService.paging(params);
        Map map = new HashMap();
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",data);
        return map;
    }

    @RequestMapping(value = "/deletePostReport",method = RequestMethod.POST)
    @RequiresRoles("admin")
    @ResponseBody
    public ApiResult deletePostReport(@RequestBody List<Integer> ids){
        sysLogService.deleteSysLog(ids);
        return ApiResult.of(ResultCode.SUCCESS);
    }
}
