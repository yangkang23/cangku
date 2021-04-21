package com.brt.oa.plan.controller;

import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.plan.pojo.Plan;
import com.brt.oa.plan.service.PlanService;
import com.brt.oa.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 问诊方案
 */
@RequestMapping(value = "/api/plan")
@RestController
public class PlanController {

    @Autowired
    PlanService planService;

    /**
     * 插入问诊方案
     * @param plan
     * @return
     */
    @PostMapping(value = "/insertPlan")
    @UserLoginToken
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public ApiResult insertPlan(@RequestBody Plan plan) {
        planService.insert(plan);
        return ApiResult.success();
    }
}
