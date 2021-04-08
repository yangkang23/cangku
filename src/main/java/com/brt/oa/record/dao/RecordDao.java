package com.brt.oa.record.dao;

import com.brt.oa.record.pojo.Record;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordDao {
    void insertRecord(Record record);
}
