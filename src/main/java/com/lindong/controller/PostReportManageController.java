package com.lindong.controller;

import com.lindong.aspect.Log;
import com.lindong.domain.PostDetails;
import com.lindong.domain.PostReport;
import com.lindong.exception.ApiResult;
import com.lindong.exception.ResultCode;
import com.lindong.service.IPostReportService;
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
@RequestMapping("stair/postReportManage")
public class PostReportManageController {

    @Resource
    private IPostReportService postReportService;
    @RequestMapping(value = "/listRecords",method = RequestMethod.POST)
    @ResponseBody
    public Map listRecords(@RequestBody Map params){
        Integer count = postReportService.getCount(params);
        List<PostReport> data = postReportService.paging(params);
        for (PostReport report : data) {
            Integer id = Integer.parseInt(report.getRp_type());
            if (id == 1){
                report.setPost(postReportService.getPost(id));
            }else{
                report.setPostDetails((PostDetails)postReportService.getObjectById(id));
            }
        }
        Map map = new HashMap();
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",data);
        return map;
    }

    @RequestMapping(value = "/disposePostReport",method = RequestMethod.POST)
    @Log(operation = "举报处理")
    @ResponseBody
    public ApiResult disposePostReport(@RequestBody Map map){
        postReportService.disposeReport(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/deletePostReport",method = RequestMethod.POST)
    @Log(operation = "删除举报数据")
    @ResponseBody
    public ApiResult deletePostReport(@RequestBody List<Integer> ids){
        postReportService.deletePostReport(ids);
        return ApiResult.of(ResultCode.SUCCESS);
    }

}
