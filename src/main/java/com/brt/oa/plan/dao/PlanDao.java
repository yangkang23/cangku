package com.brt.oa.plan.dao;

import com.brt.oa.plan.pojo.Plan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanDao {
    void insert(Plan record);
}
