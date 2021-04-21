package com.brt.oa.plan.service.impl;

import com.brt.oa.plan.dao.PlanDao;
import com.brt.oa.plan.pojo.Plan;
import com.brt.oa.plan.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    PlanDao planDao;

    @Override
    public void insert(Plan plan) {
        planDao.insert(plan);
    }
}
