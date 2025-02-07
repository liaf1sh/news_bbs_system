package com.lindong.dao;

import com.lindong.domain.Plate;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IPlateManageDao extends IBaseDao {

    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    int deletePlate(@Param("ids") List<Integer> ids);

    /**
     * 添加板块
     * @param plate
     * @return
     */
    int addPlate(Plate plate);

    /**
     * 修改板块信息
     * @param params
     * @return
     */
    int alterPlate(Map<String,String> params);

}
