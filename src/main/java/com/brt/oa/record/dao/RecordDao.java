package com.brt.oa.record.dao;

import com.brt.oa.record.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecordDao {
    void insertRecord(Record record);

    Integer findTurnover(@Param("startTime") Long startTime,@Param("endTime") Long endTime, @Param("storeid") Integer storeid);


    Integer findId(@Param("cid") Integer cid, @Param("deal_date") Long deal_date);

    List<Record> findRecord(@Param("id")Integer id);
}
