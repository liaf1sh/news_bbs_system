package com.lindong.service.impl;

import com.lindong.dao.ISysLogDao;
import com.lindong.domain.SysLog;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysLogService implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public List<Object> paging(Map<String, Object> param) {
        int currentPage = (int) param.get("currentPage");
        int pageSize = (int) param.get("pageSize");
        int index = (currentPage - 1) * pageSize;
        param.put("index",index);
        return sysLogDao.paging(param);
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return sysLogDao.getCount(param);
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
    public boolean insertSysLog(SysLog sysLog) {
        int i = sysLogDao.insertSysLog(sysLog);
        if (i == 0){
            throw new CustomException(ResultCode.SAVE_FAILED);
        }
        return true;
    }

    @Override
    public boolean deleteSysLog(List<Integer> ids) {
        int i = sysLogDao.deleteSysLog(ids);
        if (i == 0){
            throw new CustomException(ResultCode.DELETE_ERROR);
        }
        return true;
    }

}
