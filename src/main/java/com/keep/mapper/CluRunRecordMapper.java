package com.keep.mapper;

import com.keep.domain.CluRunRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p> TODO </p>
 *
 * @author zhangnan
 * @date 2019-12-08 15:46
 */
public interface CluRunRecordMapper {
    /**
     * 保存记录
     * @param record
     */
    void saveCluRunRecord(CluRunRecord record);

    /**
     * 获取全部运动记录
     * @return
     */
    List<CluRunRecord> getAllRunList(@Param("startTime") String startTime);
}
