package com.lindong.service;

import com.lindong.domain.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISysLogService extends IBaseService {

    /**
     * 记录系统操作日志
     * @param sysLog
     * @return
     */
    boolean insertSysLog(SysLog sysLog);

    /**
     * 批量删除系统操作日志数据
     * @param ids
     * @return
     */
    boolean deleteSysLog(@Param("ids") List<Integer> ids);
}
