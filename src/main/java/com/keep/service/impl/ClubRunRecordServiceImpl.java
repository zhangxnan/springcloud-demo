package com.keep.service.impl;

import com.keep.domain.CluRunRecord;
import com.keep.mapper.CluRunRecordMapper;
import com.keep.service.ClubRunRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> TODO </p>
 *
 * @author zhangnan
 * @date 2019-12-08 15:44
 */
@Service
public class ClubRunRecordServiceImpl implements ClubRunRecordService {

    @Autowired
    private CluRunRecordMapper mapper;

    @Override
    public void saveCluRunRecord(CluRunRecord record) {
        mapper.saveCluRunRecord(record);
    }

    @Override
    public List<CluRunRecord> getAllRunList(String startTime) {
        return mapper.getAllRunList(startTime);
    }
}
