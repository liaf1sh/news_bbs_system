package com.lindong.service.impl;

import com.lindong.dao.IPostReportDao;
import com.lindong.dao.IPostsDetailsManageDao;
import com.lindong.dao.IPostsManageDao;
import com.lindong.domain.Post;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IPostReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostReportService implements IPostReportService {

    @Autowired
    private IPostReportDao reportDao;
    @Autowired
    private IPostsDetailsManageDao postsDetailsManageDao;
    @Autowired
    private IPostsManageDao postsManageDao;

    @Override
    public boolean selectPostReport(Map<String, Integer> params) {
        int i = reportDao.selectPostReport(params);
        if (i > 0){
            throw new CustomException(ResultCode.REPORT_EXIST);
        }
        return true;
    }

    @Override
    public boolean insertPostReport(Map<String,String> map) {
        int i = reportDao.insertPostReport(map);
        if (i == 0){
            throw new CustomException(ResultCode.REPORT_ERROR);
        }
        return true;
    }

    @Override
    public boolean deletePostReport(List<Integer> ids) {
        int i = reportDao.deletePostReport(ids);
        if (i == 0){
            throw new CustomException(ResultCode.DELETE_ERROR);
        }
        return true;
    }

    @Override
    public Post getPost(Integer id) {
        return reportDao.getPost(id);
    }

    @Transactional(rollbackFor=CustomException.class)
    @Override
    public boolean disposeReport(Map<String, Object> params) {
        //params.put("rp_type",1);
        int id = Integer.parseInt(params.get("id").toString());
        int i1 = reportDao.alterPostReport(id);
        int i2 = reportDao.insertWarnList(params);

        if (i1 == 0 || i2 == 0){
            throw new CustomException(ResultCode.REPORT_DISPOSE_ERROR);
        }
        if (params.get("digit").equals("1")){     //举报通过,对举报数据进行处理
            List<Integer> ids = new ArrayList<>();
            if (params.get("rp_type").toString().equals("1")){       //对主题数据进行处理
                ids.add(Integer.parseInt(params.get("pid").toString()));
                Map<String,Object> map = new HashMap();
                map.put("ids",ids);
                map.put("post_status","0");
                int i = postsManageDao.alterPosts(map);
                if (i == 0){
                    throw new CustomException(ResultCode.REPORT_DISPOSE_ERROR);
                }
            }else{                      //对帖子数据进行处理
                ids.add(Integer.parseInt(params.get("pid").toString()));
                int i = postsDetailsManageDao.deletePostsDetails(ids);
                if (i == 0){
                    throw new CustomException(ResultCode.REPORT_DISPOSE_ERROR);
                }
            }
        }
        return true;
    }

    @Override
    public List<Object> paging(Map<String, Object> param) {
        Integer pageNo = (Integer) param.get("pageNo");
        Integer pageSize = (Integer) param.get("pageSize");
        Integer index = (pageNo - 1) * pageSize;
        param.put("index",index);
        return reportDao.paging(param);
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return reportDao.getCount(param);
    }

    @Override
    public Object getObjectById(Integer id) {
        return reportDao.getObjectById(id);
    }

    @Override
    public Object getObjectByName(String name) {
        return reportDao.getObjectByName(name);
    }
}
