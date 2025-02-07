package com.lindong.controller;

import com.lindong.domain.UserCollect;
import com.lindong.exception.ApiResult;
import com.lindong.exception.ResultCode;
import com.lindong.service.IUserCollectService;
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
@RequestMapping("bbs/collect")
public class UserCollectController {

    @Resource
    private IUserCollectService userCollectService;

    @RequestMapping(value = "/listCollect",method = RequestMethod.POST)
    @ResponseBody
    public Map listCollect(@RequestBody Map map){
        int total = userCollectService.getCount(map);
        List<UserCollect> collects = userCollectService.paging(map);
        Map resultMap = new HashMap();
        resultMap.put("total",total);
        resultMap.put("data",collects);
        return resultMap;
    }

    @RequestMapping(value = "/removeCollect",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult removeCollect(@RequestBody List<Integer> ids){
        userCollectService.deleteCollect(ids);
        return ApiResult.of(ResultCode.SUCCESS);
    }
}
