package com.lindong.controller;

import com.lindong.aspect.Log;
import com.lindong.domain.Notice;
import com.lindong.domain.Plate;
import com.lindong.exception.ApiResult;
import com.lindong.exception.ResultCode;
import com.lindong.service.INoticeService;
import com.lindong.service.IPlateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("stair/noticeManage")
public class NoticeManageController {
    @Resource
    private INoticeService noticeService;

    @RequestMapping("/getNoticeInfo")
    @ResponseBody
    public Map getNoticeInfo(@RequestBody Map map){
        List<Notice> list= noticeService.getNoticeList(map);
        Integer count = noticeService.getCount(map);
        map.put("code",0);
        map.put("data",list);
        map.put("count",count);
        map.put("msg","");
        return map;
    }

    @RequestMapping(value = "/deleteNoticeById",method = RequestMethod.POST)
    @Log(operation = "删除公告")
    @ResponseBody
    public ApiResult deleteNoticeById(@RequestBody List<Integer> ids){
        System.out.println("我的id是"+ids);
        noticeService.deleteNoticeById(ids);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/updateNoticeById",method = RequestMethod.POST)
    @Log(operation = "公告更新")
    @ResponseBody
    public ApiResult updateNoticeById(@RequestBody Map map){
        noticeService.updateNoticeById(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/publishNotice",method = RequestMethod.POST)
    @Log(operation = "添加公告")
    @ResponseBody
    public ApiResult publishNotice(@RequestBody Map map){
        noticeService.publishNotice(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }
}
