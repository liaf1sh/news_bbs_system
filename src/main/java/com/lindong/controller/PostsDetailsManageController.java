package com.lindong.controller;

import com.lindong.aspect.Log;
import com.lindong.domain.PostDetails;
import com.lindong.exception.ApiResult;
import com.lindong.exception.ResultCode;
import com.lindong.service.IPostsDetailsManageService;
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
@RequestMapping("stair/postsDetailsManage")
public class PostsDetailsManageController {

    @Resource
    private IPostsDetailsManageService postsDetailsManageService;

    @RequestMapping(value = "listRecords",method = RequestMethod.POST)
    @ResponseBody
    public Map listRecords(@RequestBody Map params){
        List<PostDetails> data = postsDetailsManageService.paging(params);
        Integer total = postsDetailsManageService.getCount(params);
        Map map = new HashMap();
        map.put("count",total);
        map.put("data",data);
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @RequestMapping(value = "deletePostsDetails",method = RequestMethod.POST)
    @RequiresRoles("boardmaster")
    @Log(operation = "删除评论(回帖)数据")
    @ResponseBody
    public ApiResult deletePostsDetails(@RequestBody List<Integer> ids){
        postsDetailsManageService.deletePostsDetails(ids);
        return ApiResult.of(ResultCode.SUCCESS);
    }

}
