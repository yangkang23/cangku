package com.brt.oa.record.service.impl;

import com.brt.oa.record.dao.RecordDao;
import com.brt.oa.record.pojo.Record;
import com.brt.oa.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordDao recordDao;

    @Override
    public void insertRecord(Record record) {
        recordDao.insertRecord(record);
    }

    @Override
    public Double findTurnover(Long startTime, Long endTime, Integer storeid) {
        return recordDao.findTurnover(startTime,endTime,storeid);
    }

    @Override
    public Integer findId(Integer cid, Long deal_date) {
        return recordDao.findId(cid,deal_date);
    }

    @Override
    public List<Record> findRecord(Integer storid,Integer pageIndex,Integer pageSize) {
        Integer start = (pageIndex-1)*pageSize;
        Integer size = pageSize;
        return recordDao.findRecord(storid,start,size);
    }

    @Override
    public void findRecordLimt(Integer storeid, int i) {

    }

    @Override
    public Integer findTotal(Integer storeid) {

        return recordDao.findTotal(storeid);
    }

    @Override
    public void deleteRecordById(Integer id,Integer state) {
        recordDao.deleteRecordById(id,state);

    }
}
