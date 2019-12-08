package com.keep.service;

import com.keep.domain.CluRunRecord;

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

}
