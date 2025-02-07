package com.lindong.controller;

import com.lindong.domain.Post;
import com.lindong.domain.User;
import com.lindong.exception.ApiResult;
import com.lindong.exception.ResultCode;
import com.lindong.service.IAttentionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("bbs/care")
public class AttentionController {

    @Resource
    private IAttentionService attentionService;

    @RequestMapping("/listCare")
    @ResponseBody
    public Map listCare(@RequestBody Map map){
        int total = attentionService.getCount(map);
        List<User> users = attentionService.paging(map);
        map.put("total",total);
        map.put("data",users);
        return map;
    }

    @RequestMapping(value = "/deleteCare",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult deleteCare(@RequestBody Map map){
        System.out.println(map.get("uid") +"======"+ map.get("aid"));
        attentionService.deleteAttention((Integer)map.get("uid"),(Integer)map.get("aid"));
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/listCarePosts",method = RequestMethod.GET)
    @ResponseBody
    public List<Post> listCarePosts(Integer uid){
        return attentionService.getCarePosts(uid);
    }

    @RequestMapping(value = "/selectCare",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult selectCare(@RequestBody Map map){
        attentionService.selectCare(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }

}
