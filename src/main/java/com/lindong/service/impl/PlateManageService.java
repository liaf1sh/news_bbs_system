package com.lindong.service.impl;

import com.lindong.dao.IPlateManageDao;
import com.lindong.domain.Plate;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IPlateManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlateManageService implements IPlateManageService {

    @Autowired
    private IPlateManageDao plateManageDao;

    @Override
    public List<Object> paging(Map<String, Object> param) {
        int pageNo = (Integer) param.get("pageNo");
        int pageSize = (Integer) param.get("pageSize");
        int index = (pageNo - 1) * pageSize;
        param.put("index",index);
        List<Object> data = plateManageDao.paging(param);
        return data;
    }

    @Override
    public int getCount(Map<String, Object> param) {
        return plateManageDao.getCount(param);
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
    public boolean deletePlate(List<Integer> ids) {
        int i = plateManageDao.deletePlate(ids);
        if (i == 0){
            throw new CustomException(ResultCode.DELETE_ERROR);
        }
        return true;
    }

    @Override
    public boolean addPlate(Plate plate) {
        int i = plateManageDao.addPlate(plate);
        if (i == 0){
            throw new CustomException(ResultCode.SAVE_FAILED);
        }
        return true;
    }

    @Override
    public boolean alterPlate(Map<String, String> params) {
        int i = plateManageDao.alterPlate(params);
        if (i == 0){
            throw new CustomException(ResultCode.UPDATE_FAILED);
        }
        return true;
    }
}
