package com.lindong.service.impl;

import com.lindong.dao.IPlateDao;
import com.lindong.domain.Notice;
import com.lindong.domain.Plate;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IPlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlateService implements IPlateService {

    @Autowired
    private IPlateDao plateDao;

    @Override
    public List<Plate> findAll() {
        return plateDao.findAll();
    }

    @Override
    public Plate getPlateById(Integer plate_id) {
        return plateDao.getPlateById(plate_id);
    }

    @Override
    public boolean updatePlate(Plate plate) {
        int i = plateDao.updatePlate(plate);
        if (i == 0){
            throw new CustomException(ResultCode.UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public Plate getPlatePostInfo(Map map) {
        int pageCurrent = (Integer)map.get("currentPage"); //当前页
        int pageSize = (Integer)map.get("pageSize");  //页数
        int pageStart = (pageCurrent-1)*pageSize;
        map.put("pageStart",pageStart);
        return plateDao.getPlatePostInfo(map);
    }

    @Override
    public Plate getIstopPostInfo(Map map) {
        return plateDao.getIstopPostInfo(map);
    }

    @Override
    public Integer getCount(Map map) {
        return plateDao.getCount(map);
    }

    @Override
    public Integer updatePlateInfo(Map map) {
        return plateDao.updatePlateInfo(map);
    }

    @Override
    public List<Plate> plateRanking() {
        return plateDao.plateRanking();
    }

    @Override
    public Notice selectNotice(Integer id) {
        return  plateDao.selectNotice(id);
    }

}
