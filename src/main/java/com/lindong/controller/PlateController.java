package com.lindong.controller;


import com.alibaba.fastjson.JSON;
import com.lindong.domain.Notice;
import com.lindong.domain.Plate;
import com.lindong.service.IPlateService;
import com.lindong.utils.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("bbs/plate")
public class PlateController {

    @Resource
    private IPlateService plateService;
    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Plate> findAll(){
        List<Plate> plates;
        String plateList= (String)redisUtil.get("plate:all");
        if (!StringUtils.isEmpty(plateList)){
            String newPlateList =  plateList.replace("学院专区","新闻专区").replace("社团专区","文章专区").replace("校园趣事","世界趣事").replace("游戏交流","新闻交流").replace("告白墙","树洞").replace("学校资源共享","资源共享").replace("编程开发","兴趣开发");
            System.out.println("-------获取缓存--------");
            plates = JSON.parseArray(newPlateList, Plate.class);
            return plates;
        }
        System.out.println("-------数据库查询,并存入redis缓存--------");
        plates = plateService.findAll();
        redisUtil.set("plate:all", JSON.toJSONString(plates));
        return plates;
    }

    @RequestMapping("/getPlate")
    @ResponseBody
    public Plate getPlate(Integer plate_id){
        Plate plate = plateService.getPlateById(plate_id);
        return plate;
    }

    @RequestMapping("/getPlatePostInfo")
    @ResponseBody
    public Map getPlatePostInfo(@RequestBody Map map){
        // System.out.println("我是"+map.get("pl_type"));
        Plate list = plateService.getPlatePostInfo(map); //非置顶信息
        Plate istopPostlist= plateService.getIstopPostInfo(map); //置顶信息
        System.out.println("有"+plateService.getCount(map));
        map.put("totalCount",plateService.getCount(map));
        //map.put("todayPost",plateService.getCount())
        Integer pl_type = Integer.parseInt(String.valueOf(map.get("pl_type")));
        map.put("plateInfo",plateService.getPlateById(pl_type));
        map.put("plateRanking",plateService.plateRanking());
        map.put("list",list);
        map.put("list2",istopPostlist);
        return map;
    }
    @RequestMapping("/getTodayPostNum")
    @ResponseBody
    public Integer getTodayPostNum(@RequestBody Map map){
        System.out.println("今日发表数量:"+plateService.getCount(map));
        return plateService.getCount(map);
    }

    @RequestMapping("selectNotice")
    @ResponseBody
    public Notice findNotice(Integer id){
        Notice notices =  plateService.selectNotice(id);
        return  notices;
    }
}
