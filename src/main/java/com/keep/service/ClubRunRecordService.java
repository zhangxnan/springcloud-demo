package com.keep.service;

import com.keep.domain.CluRunRecord;

import java.util.List;

/**
 * <p> 活动记录 </p>
 *
 * @author zhangnan
 * @date 2019-12-08 15:44
 */
public interface ClubRunRecordService {

    /**
     * 保存一个活动记录
     * @param record
     */
    void saveCluRunRecord(CluRunRecord record);

    /**
     * 获取全部运动记录
     * @return 运动记录
     */
    List<CluRunRecord> getAllRunList(String startTime);
}
