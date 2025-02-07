package com.lindong.service;

import com.lindong.domain.Plate;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IPlateManageService extends IBaseService {

    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    boolean deletePlate(@Param("ids") List<Integer> ids);

    /**
     * 添加板块
     * @param plate
     * @return
     */
    boolean addPlate(Plate plate);

    /**
     * 修改板块信息
     * @param params
     * @return
     */
    boolean alterPlate(Map<String,String> params);
}
