package com.brt.oa.plan.service.impl;

import com.brt.oa.plan.dao.PlanDao;
import com.brt.oa.plan.pojo.Conditioning;
import com.brt.oa.plan.pojo.Discomfort;
import com.brt.oa.plan.pojo.Ear;
import com.brt.oa.plan.pojo.Plan;
import com.brt.oa.plan.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    PlanDao planDao;

    @Override
    public void insert(Plan plan) {
        planDao.insert(plan);
    }

    @Override
    public void insertEar(List<Ear> earList) {
        planDao.insertEar(earList);
    }

    @Override
    public void insertDiscomfort(List<Discomfort> discomfortList) {
        planDao.insertDiscomfort(discomfortList);
    }

    @Override
    public void insertConditioning(List<Conditioning> conditioningList) {
        planDao.insertConditioning(conditioningList);
    }

    @Override
    public List<Plan> findPlanByCid(Integer cid) {
        return planDao.findPlanByCid(cid);
    }

    @Override
    public List<Conditioning> findConditioningByPlanid(Integer planid) {
        return planDao.findConditioningByPlanid(planid);
    }

    @Override
    public void updateTagAndRidById(Integer id, Integer tag, Integer rid) {
        planDao.updateTagAndRidById(id, tag, rid);
    }

    @Override
    public List<Conditioning> findConditioningByRid(Integer rid) {
        return planDao.findConditioningByRid(rid);
    }

    @Override
    public List<Discomfort> findDiscomfortByPlanid(Integer planid) {
        return planDao.findDiscomfortByPlanid(planid);
    }

    @Override
    public List<Ear> findEarByPlanid(Integer planid) {
        return planDao.findEarByPlanid(planid);
    }

    @Override
    public void updateTimeById(Integer id, Long startTime, Long endTime) {
        planDao.updateTimeById(id, startTime, endTime);
    }

    @Override
    public void updateRemakeById(Integer id, String remark) {
        planDao.updateRemakeById(id, remark);
    }
}
