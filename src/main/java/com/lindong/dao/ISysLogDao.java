package com.lindong.dao;

import com.lindong.domain.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISysLogDao extends IBaseDao {

    /**
     * 记录系统操作日志
     * @param sysLog
     * @return
     */
    int insertSysLog(SysLog sysLog);

    /**
     * 批量删除系统操作日志数据
     * @param ids
     * @return
     */
    int deleteSysLog(@Param("ids") List<Integer> ids);
}
