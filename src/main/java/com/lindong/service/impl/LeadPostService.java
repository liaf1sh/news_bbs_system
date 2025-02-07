package com.lindong.service.impl;

import com.lindong.dao.ILeadPostDao;
import com.lindong.dao.IPlateDao;
import com.lindong.domain.Plate;
import com.lindong.domain.Post;
import com.lindong.service.ILeadPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class LeadPostService implements ILeadPostService {
    @Resource
    private ILeadPostDao iLeadPostDao;
    @Resource
    private IPlateDao iPlateDao;

    @Override
    /*分页查看最新热门*/
    public List<Post> paging(Map map) {
        int pageCurrent = (Integer)map.get("pageCurrent"); //当前页
        int pageSize = (Integer)map.get("pageSize");  //页数
        int pageStart = (pageCurrent-1)*pageSize;
        map.put("pageStart",pageStart);
        List<Post> list = iLeadPostDao.paging(map);
        return list;
    }

    @Override
    public Integer insertNewPost(Post post) {
        Integer count = iLeadPostDao.insertNewPost(post);
        Integer todayNewPost = iLeadPostDao.getTodayNewPostCount(post.getUser_id());
        if(todayNewPost<=3){
            iLeadPostDao.updateExperience(post.getUser_id(),10);
        }
        Plate plate = new Plate();
        plate.setTheme(1);
        plate.setId(post.getPlate_id());
        iPlateDao.updatePlate(plate);
        return count ;
    }

    @Override
    public int getBrowseNumber() {
        return iLeadPostDao.getBrowseNumber();
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return iLeadPostDao.getCount(param);
    }

}
