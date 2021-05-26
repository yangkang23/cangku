package com.brt.oa.record.dao;

import com.brt.oa.record.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecordDao {
    void insertRecord(Record record);

    Double findTurnover(@Param("startTime") Long startTime, @Param("endTime") Long endTime, @Param("storeid") Integer storeid);


    Integer findId(@Param("cid") Integer cid, @Param("deal_date") Long deal_date);

    List<Record> findRecord(@Param("storeid") Integer storeid, @Param("start") Integer start, @Param("size") Integer size, @Param("customer_name") String customer_name);

    Integer findTotal(@Param("storeid") Integer storeid, @Param("customer_name") String customer_name);

    void deleteRecordById(@Param("id") Integer id, @Param("state") Integer state);
}
