package com.lindong.service;


import java.util.List;
import java.util.Map;

/**
 * 相同功能的公共业务层service接口
 */
public interface IBaseService {

    /**
     * 查询分页数据
     * @param param
     * @return
     */
    List<Object> paging(Map<String,Object> param);

    /**
     * 带条件的分页查询数据
     * @param param
     * @return
     */
    int getCount(Map<String, Object> param);

    /**
     * 根据id查询一条数据
     * @param id
     * @return
     */
    Object getObjectById(Integer id);

    /**
     * 根据名称查询一条数据
     * @param name
     * @return
     */
    Object getObjectByName(String name);


}
