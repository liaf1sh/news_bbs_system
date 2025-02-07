package com.lindong.controller;

import com.lindong.aspect.Log;
import com.lindong.domain.Post;
import com.lindong.exception.ApiResult;
import com.lindong.exception.ResultCode;
import com.lindong.service.IPostsManageService;
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
@RequestMapping("stair/postsManage")
public class PostsManageController {

    @Resource
    private IPostsManageService postsManageService;

    @RequestMapping(value = "/listRecords",method = RequestMethod.POST)
    @ResponseBody
    public Map listRecords(@RequestBody Map params){
        Integer count = postsManageService.getCount(params);
        List<Post> data = postsManageService.paging(params);
        Map map = new HashMap();
        map.put("count",count);
        map.put("data",data);
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @RequestMapping(value = "/changPosts",method = RequestMethod.POST)
    @RequiresRoles("boardmaster")
    @Log(operation = "修改帖子")
    @ResponseBody
    public ApiResult changPosts(@RequestBody Map map){
        postsManageService.alterPosts(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/deletePosts",method = RequestMethod.POST)
    @RequiresRoles("boardmaster")
    @Log(operation = "删除帖子")
    @ResponseBody
    public ApiResult deletePosts(@RequestBody List<Integer> ids){
        postsManageService.deletePosts(ids);
        return ApiResult.of(ResultCode.SUCCESS);
    }

}
