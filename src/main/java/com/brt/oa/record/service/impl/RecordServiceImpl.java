package com.brt.oa.record.service.impl;

import com.brt.oa.record.dao.RecordDao;
import com.brt.oa.record.pojo.Record;
import com.brt.oa.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordDao recordDao;

    @Override
    public void insertRecord(Record record) {
        recordDao.insertRecord(record);
    }

    @Override
    public Integer findTurnover(Long startTime, Long endTime, Integer storeid) {
        return recordDao.findTurnover(startTime,endTime,storeid);
    }
}
