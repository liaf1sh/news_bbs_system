package com.lindong.controller;

import com.lindong.domain.Post;
import com.lindong.domain.User;
import com.lindong.service.ILeadUserService;
import com.lindong.service.ILeadPostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lindong.utils.DateUtil.strToDate;

@Controller
@RequestMapping("bbs/userPost")
public class LeadPostController {
    @Resource
    private ILeadPostService iPostService;
    @Resource
    private ILeadUserService iLeadUserService;

    @ResponseBody
    @RequestMapping("/findClassifyPost")
    public Map findClassifyPost(@RequestParam("PageSize") Integer pageSize,@RequestParam("PageCurrent") Integer pageCurrent,@RequestParam("chooseSelect") String chooseSelect,@RequestParam("userId") Integer userId ){
        Map map = new HashMap();
        map.put("pageSize",pageSize);   //每页数据量
        map.put("pageCurrent",pageCurrent); //当前页码
        map.put("chooseSelect",chooseSelect); //查看类别
        map.put("userId",userId);       //登录用户的id
        System.out.println( "我是查看模块"+map.get("chooseSelect"));
        List<Post> list = iPostService.paging(map);
        Integer pageTotal = iPostService.getCount(map); //数据总数
        map.put("list",list);
        map.put("total",pageTotal);
        return map;
    }
    @ResponseBody
    @RequestMapping("/leadUserInfo")
    public User leadUserInfo(@RequestParam("userId") Integer userId){
        User user = iLeadUserService.getUserInfoById(userId);
        return user;
    }
    /*发表帖子*/
    @ResponseBody
    @RequestMapping("/insertPost")
    public Integer insertPost(@RequestBody Map map){
        Post post = new Post();
        post.setUser_id((Integer) map.get("userId")); //获取当前用户id
        post.setTitle((String)map.get("title"));
        post.setPlate_id(Integer.parseInt(String.valueOf( map.get("plateId")))); //获取帖子所属版块id
        post.setPost_author((String)map.get("postAuthor")); //获取帖子作者
       /* Date publish_time = strToDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(map.get("publishTime")));
        post.setPublish_time(publish_time); //获取帖子发表的时间*/
        post.setContent((String)map.get("content"));  //获取发表帖子的内容
        post.setLast_reply((String)map.get("lastReply")); //获取最后回复者
        //post.setLast_reply_time((Date)map.get("lastReplyTime")); //获取最后回复时间
        Integer result = iPostService.insertNewPost(post);
        return result;
    }
}
