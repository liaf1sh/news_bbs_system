package com.lindong.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lindong.domain.Plate;
import com.lindong.domain.Post;
import com.lindong.service.IBaseService;
import com.lindong.service.IRankService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("bbs/rank")
public class RankController {

    @Resource
    private IRankService iRankService;


    @RequestMapping(value = "query")
    @ResponseBody
    public List<Post> selectIntegral(@RequestBody Map map){
        List<Post> data = (List<Post>) iRankService.paging(map);
        return data;
    }
    @RequestMapping(value = "selectExperience")
    @ResponseBody
    public List<Post> selectExperience(@RequestBody Map map){
        List<Post> data = (List<Post>) iRankService.experienceByuserId(map);
        return data;
    }
    @RequestMapping(value = "selectReply")
    @ResponseBody
    public List<Post> selectReply(@RequestBody Map map){
        List<Post> data = (List<Post>) iRankService.replyByplateId(map);
        return data;
    }
    @RequestMapping(value = "selectBrowse")
    @ResponseBody
    public List<Post> selectBrowse(@RequestBody Map map){
        List<Post> data = (List<Post>) iRankService.browseByplateId(map);
        return data;
    }

    @RequestMapping(value = "selectShare")
    @ResponseBody
    public List<Post> selectShare(@RequestBody Map map){
        List<Post> data = (List<Post>) iRankService.shareByplateId(map);
        return data;
    }
    @RequestMapping(value = "selectCollect")
    @ResponseBody
    public List<Post> selectCollect(@RequestBody Map map){
        List<Post> data = (List<Post>) iRankService.collectByplateId(map);
        return data;
    }
    @RequestMapping(value = "selectHeat")
    @ResponseBody
    public List<Post> selectHeat(@RequestBody Map map){
        List<Post> data = (List<Post>) iRankService.heatByplateId(map);
        return data;
    }
    @RequestMapping(value = "selectTheme")
    @ResponseBody
    public List<Post> selectTheme(@RequestBody Map map){
        List<Post> data = (List<Post>) iRankService.themeByplateId(map);
        return data;
    }
    @RequestMapping(value = "selectPosts")
    @ResponseBody
    public List<Post> selectPosts(@RequestBody Map map){
        List<Post> data = (List<Post>) iRankService.postsByplateId(map);
        return data;
    }
    @RequestMapping(value = "selectPost")
    @ResponseBody
    public List<Post> selectPost(@RequestBody Map map){
        List<Post> data = (List<Post>) iRankService.postsByuserId(map);
        return data;
    }
    @RequestMapping(value = "selectLog")
    @ResponseBody
    public List<Post> selectLogs(@RequestBody Map map){
        List<Post> data = (List<Post>) iRankService.logsByuserId(map);
        return data;
    }
    @RequestMapping(value = "selectTime")
    @ResponseBody
    public JSONArray selectTime(){
        List<Plate> data = iRankService.timeByuserId();
        /*for (Plate datum : data) {
            System.out.println(datum.getTodayTotalTheme());
        }*/
        return (JSONArray)JSON.toJSON(data);
    }
}
