package com.lindong.controller;

import com.lindong.exception.ApiResult;
import com.lindong.exception.ResultCode;
import com.lindong.service.IPostReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/bbs/postReport")
public class PostReportController {

    @Resource
    private IPostReportService postReportSevice;

    @RequestMapping("/executeReport")
    @ResponseBody
    public ApiResult executeReport(@RequestBody Map params){
        //查询用户是否已经举报过
        postReportSevice.selectPostReport(params);
        //插入举报数据
        postReportSevice.insertPostReport(params);
        return ApiResult.of(ResultCode.SUCCESS);
    }

}
