package com.lindong.service.impl;

import com.lindong.dao.IRankDao;
import com.lindong.domain.Plate;
import com.lindong.service.IRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class RankService implements IRankService {

    @Autowired
    private IRankDao iRankDao;


    @Override
    public List<Object> paging(Map<String, Object> param) {
        Integer pageSize = (Integer)param.get("pageSize");
        Integer pageNo = (Integer)param.get("pageNo");
        int index = (pageNo-1) * pageSize;
        param.put("index",index);
        return iRankDao.paging(param);
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return iRankDao.getCount(param);
    }

    @Override
    public Object getObjectById(Integer id) {
        return null;
    }

    @Override
    public Object getObjectByName(String name) {
        return null;
    }

    @Override
    public List<Object> experienceByuserId(Map<String, Object> param) {
        Integer pageSize = (Integer)param.get("pageSize");
        Integer pageNo = (Integer)param.get("pageNo");
        int index = (pageNo-1) * pageSize;
        param.put("index",index);
        return iRankDao.experienceByuserId(param);
    }

    @Override
    public List<Object> replyByplateId(Map<String, Object> param) {
        Integer pageSize = (Integer)param.get("pageSize");
        Integer pageNo = (Integer)param.get("pageNo");
        int index = (pageNo-1) * pageSize;
        param.put("index",index);
        return iRankDao.replyByplateId(param);
    }

    @Override
    public List<Object> browseByplateId(Map<String, Object> param) {
        Integer pageSize = (Integer)param.get("pageSize");
        Integer pageNo = (Integer)param.get("pageNo");
        int index = (pageNo-1) * pageSize;
        param.put("index",index);
        return iRankDao.browseByplateId(param);
    }

    @Override
    public List<Object> shareByplateId(Map<String, Object> param) {
        Integer pageSize = (Integer)param.get("pageSize");
        Integer pageNo = (Integer)param.get("pageNo");
        int index = (pageNo-1) * pageSize;
        param.put("index",index);
        return iRankDao.shareByplateId(param);
    }

    @Override
    public List<Object> collectByplateId(Map<String, Object> param) {
        Integer pageSize = (Integer)param.get("pageSize");
        Integer pageNo = (Integer)param.get("pageNo");
        int index = (pageNo-1) * pageSize;
        param.put("index",index);
        return iRankDao.collectByplateId(param);
    }
    @Override
    public List<Object> heatByplateId(Map<String, Object> param) {
        Integer pageSize = (Integer)param.get("pageSize");
        Integer pageNo = (Integer)param.get("pageNo");
        int index = (pageNo-1) * pageSize;
        param.put("index",index);
        return iRankDao.heatByplateId(param);
    }
    @Override
    public List<Object> themeByplateId(Map<String, Object> param) {
        Integer pageSize = (Integer)param.get("pageSize");
        Integer pageNo = (Integer)param.get("pageNo");
        int index = (pageNo-1) * pageSize;
        param.put("index",index);
        return iRankDao.themeByplateId(param);
    }
    @Override
    public List<Object> postsByplateId(Map<String, Object> param) {
        Integer pageSize = (Integer)param.get("pageSize");
        Integer pageNo = (Integer)param.get("pageNo");
        int index = (pageNo-1) * pageSize;
        param.put("index",index);
        return iRankDao.postsByplateId(param);
    }
    @Override
    public List<Object> postsByuserId(Map<String, Object> param) {
        Integer pageSize = (Integer)param.get("pageSize");
        Integer pageNo = (Integer)param.get("pageNo");
        int index = (pageNo-1) * pageSize;
        param.put("index",index);
        return iRankDao.postsByuserId(param);
    }
    @Override
    public List<Object> logsByuserId(Map<String, Object> param) {
        Integer pageSize = (Integer)param.get("pageSize");
        Integer pageNo = (Integer)param.get("pageNo");
        int index = (pageNo-1) * pageSize;
        param.put("index",index);
        return iRankDao.logsByuserId(param);
    }
    @Override
    public List<Plate> timeByuserId() {
        return iRankDao.timeByuserId();
    }
}
