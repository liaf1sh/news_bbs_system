package com.lindong.service;

import com.lindong.domain.Notice;
import com.lindong.domain.Plate;

import java.util.List;
import java.util.Map;

public interface IPlateService {


    /**
     * 查询所有版块
     * @return
     */
    List<Plate> findAll();

    /**
     * 根据板块id获取对应板块
     * @param plate_id
     * @return
     */
    Plate getPlateById(Integer plate_id);

    /**
     * 更新板块数据
     * @param plate
     * @return
     */
    boolean updatePlate(Plate plate);

    /*查看版块主题信息*/
    Plate getPlatePostInfo(Map map);
    /*获取置顶主题的数据*/
    Plate getIstopPostInfo(Map map);
    /*查看所查板块的主题数量*/
    Integer getCount(Map map);
    /*更新版块基本信息*/
    Integer updatePlateInfo(Map map);
    /*查看版块排名*/
    List<Plate> plateRanking();
    //版块公告查询
    Notice selectNotice(Integer  id);
}
