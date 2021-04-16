package com.brt.oa.record.service;

import com.brt.oa.record.pojo.Record;

import java.util.List;

public interface RecordService {
    void insertRecord(Record record);

    Double findTurnover(Long startTime, Long endTime, Integer storeid);

    Integer findId(Integer cid, Long deal_date);

    List<Record> findRecord(Integer store,Integer pageIndex,Integer pageSize);

    void findRecordLimt(Integer storeid, int i);

    Integer findTotal(Integer storeid);

    void deleteRecordById(Integer id,Integer state);
}
