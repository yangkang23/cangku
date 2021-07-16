package com.brt.oa.plan.service;

import com.brt.oa.plan.pojo.Conditioning;
import com.brt.oa.plan.pojo.Discomfort;
import com.brt.oa.plan.pojo.Ear;
import com.brt.oa.plan.pojo.Plan;

import java.util.List;

public interface PlanService {
       void insert(Plan plan);

    void insertEar(List<Ear> earList);

    void insertDiscomfort(List<Discomfort> discomfortList);

    void insertConditioning(List<Conditioning> conditioningList);

    List<Plan> findPlanByCid(Integer cid);

    List<Conditioning> findConditioningByPlanid(Integer planid);

    void updateTagAndRidById(Integer id, Integer tag, Integer rid);

    List<Conditioning> findConditioningByRid(Integer rid);

    List<Discomfort> findDiscomfortByPlanid(Integer planid);

    List<Ear> findEarByPlanid(Integer planid);

    void updateTimeById(Integer id, Long startTime, Long endTime,Double fee);

    void updateRemakeById(Integer id, String remark);
}
