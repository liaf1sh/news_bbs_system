package com.lindong.controller;

import com.lindong.domain.Plate;
import com.lindong.domain.Post;
import com.lindong.domain.PostDetails;
import com.lindong.domain.UserCollect;
import com.lindong.exception.ApiResult;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IPlateService;
import com.lindong.service.IPostBrowseCommentService;
import com.lindong.service.IPostShareService;
import com.lindong.service.IUserCollectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("bbs/postBrowse")
public class PostBrowseCommentController {

    @Resource
    private IPostBrowseCommentService postBrowseService;
    @Resource
    private IUserCollectService userCollectService;
    @Resource
    private IPlateService plateService;
    @Resource
    private IPostShareService postShareService;

    @RequestMapping("/findNewestPost")
    @ResponseBody
    public Map findNewestPost(){
        Map<String,String> map = new HashMap<>();
        //最新热度
        map.put("orderBy","post_heat");
        List<Post> postHeats = postBrowseService.selectNewestPosts(map);
        //最新发表
        map.put("orderBy","id");
        List<Post> postPublishs = postBrowseService.selectNewestPosts(map);
        //最新回复
        map.put("orderBy","last_reply_time");
        List<Post> postlastReplys = postBrowseService.selectNewestPosts(map);
        //最新精华
        map.put("orderBy","id");
        map.put("post_status","4");
        List<Post> postEssences = postBrowseService.selectNewestPosts(map);
        Map resultMap = new HashMap();
        resultMap.put("postHeats",postHeats);
        resultMap.put("postPublishs",postPublishs);
        resultMap.put("postlastReplys",postlastReplys);
        resultMap.put("postEssences",postEssences);
        //System.out.println("============="+JSON.toJSONString(resultMap));
        return resultMap;
    }

    @RequestMapping(value = "listPosts",method = RequestMethod.POST)
    @ResponseBody
    public Map listPosts(@RequestBody(required = true) Map map){    //必须为post请求
        int total = postBrowseService.getCount(map);
        List<Post> data = postBrowseService.paging(map);
        for (Post datum : data) {
            Integer uid = datum.getUser_id();
            Integer experience = postBrowseService.getExperienceById(uid);
            datum.setTotal_experience(experience);
            datum.setThemeCount(postBrowseService.getThemeCountById(uid));
            datum.setPostsCount(postBrowseService.getPostsCountById(uid));
            datum.setGrade(postBrowseService.getGrade(experience));
            for (PostDetails listPost : datum.getListPosts()) {
                Integer pd_uid = listPost.getPd_uid();
                Integer pd_experience = postBrowseService.getExperienceById(pd_uid);
                listPost.setTotal_experience(pd_experience);
                listPost.setThemeCount(postBrowseService.getThemeCountById(pd_uid));
                listPost.setPostsCount(postBrowseService.getPostsCountById(pd_uid));
                listPost.setGrade(postBrowseService.getGrade(pd_experience));
            }
        }
        Map resultMap = new HashMap();
        resultMap.put("total",total);
        resultMap.put("data",data);
        //System.out.println("========="+JSON.toJSONString(resultMap));
        return resultMap;
    }

    @RequestMapping(value = "/postReply",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult postReply(@RequestBody(required = true) Map map){
        int count = postBrowseService.getCount(map);    //获取指定主题总回复条数
        PostDetails postDetails = new PostDetails();
        //map.put("id",postDetails.getPost_id());
        postDetails.setPd_uid((Integer) map.get("pd_uid"));
        postDetails.setPost_id((Integer) map.get("post_id"));
        postDetails.setPd_content((String) map.get("pd_content"));
        postDetails.setSeat(++count+"");
        postBrowseService.insertPostDetails(postDetails);
        map.put("id",map.get("post_id"));
        map.put("reply_count",1);
        map.put("last_reply_time",1);
        postBrowseService.updatePost(map);      //更新主题数据
        Plate plate = new Plate();
        plate.setId((Integer) map.get("plate_id"));
        plate.setPosts(1);
        plateService.updatePlate(plate);    //更新板块中的帖子数
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping("/updatePosts")
    @ResponseBody
    public ApiResult updatePosts(@RequestBody Map map){
        boolean isOperation = postBrowseService.selectThemeOperation(map);
        if (isOperation){   //判断用户是否对主题已经评价过
            throw new CustomException(ResultCode.POST_OPERATION_FAILED);
        }
        postBrowseService.insertThemeOperation(map);
        postBrowseService.updatePost(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/postsCollect",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult postsCollect(@RequestBody Map map){
        userCollectService.selectCollectCountByMap(map);    //判断帖子是否收藏,若收藏则抛异常
        UserCollect userCollect = new UserCollect();
        userCollect.setU_id((Integer)map.get("u_id"));
        userCollect.setAll_id((Integer)map.get("id"));
        userCollect.setTitle((String)map.get("title"));
        userCollect.setSource_plate((String)map.get("source_plate"));
        userCollect.setType((String)map.get("type"));
        userCollectService.insertUserCollect(userCollect);
        map.put("post_collect",1);
        postBrowseService.updatePost(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/postsShare",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult postsShare(@RequestBody Map<String,String> map){
        //判断帖子是否已分享过,否则抛异常
        postShareService.selectPostShareExist(map);
        //添加帖子分享数据
        postShareService.insertPostShare(map);
        map.put("id",map.get("post_id"));   //帖子主题id
        map.put("share_count","1"); //表示分享次数+1
        //更新帖子分享数
        postBrowseService.updatePost(map);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/addAttention",method =RequestMethod.POST)
    @ResponseBody
    public ApiResult addAttention(@RequestBody Map map){
        postBrowseService.insertAttention((Integer)map.get("uid"), (Integer)map.get("aid"));
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping("/clickAddBrowseCount")
    @ResponseBody
    public Integer clickAddBrowseCount(@RequestParam("id") Integer id ){
        return postBrowseService.clickAddBrowseCount(id);
    }

    @RequestMapping("/getSearchPosts")
    @ResponseBody
    public Map getSearchPosts(@RequestBody Map map){
        return postBrowseService.getSearchPosts(map);
    }

}
