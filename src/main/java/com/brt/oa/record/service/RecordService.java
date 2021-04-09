package com.brt.oa.record.service;

import com.brt.oa.record.pojo.Record;

public interface RecordService {
    void insertRecord(Record record);

    Integer findTurnover(Long startTime, Long endTime, Integer storeid);
}
