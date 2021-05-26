package com.brt.oa.plan.dao;

import com.brt.oa.plan.pojo.Conditioning;
import com.brt.oa.plan.pojo.Discomfort;
import com.brt.oa.plan.pojo.Ear;
import com.brt.oa.plan.pojo.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlanDao {
    void insert(Plan record);

    void insertEar(List<Ear> earList);

    void insertDiscomfort(List<Discomfort> discomfortList);

    void insertConditioning(List<Conditioning> conditioningList);

    List<Plan> findPlanByCid(Integer cid);

    List<Conditioning> findConditioningByPlanid(Integer planid);

    void updateTagAndRidById(@Param("id") Integer id, @Param("tag") Integer tag, @Param("rid") Integer rid);

    List<Conditioning> findConditioningByRid(Integer rid);

    List<Discomfort> findDiscomfortByPlanid(Integer planid);

    List<Ear> findEarByPlanid(Integer planid);

    void updateTimeById(@Param("id") Integer id, @Param("startTime") Long startTime, @Param("endTime") Long endTime);


    void updateRemakeById(@Param("id") Integer id, @Param("remark") String remark);
}
