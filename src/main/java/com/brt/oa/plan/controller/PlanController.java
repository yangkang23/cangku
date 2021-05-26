package com.brt.oa.plan.controller;

import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.plan.pojo.Conditioning;
import com.brt.oa.plan.pojo.Discomfort;
import com.brt.oa.plan.pojo.Ear;
import com.brt.oa.plan.pojo.Plan;
import com.brt.oa.plan.service.PlanService;
import com.brt.oa.product.service.ProductService;
import com.brt.oa.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 问诊方案
 */
@RequestMapping(value = "/api/plan")
@Slf4j
@RestController
public class PlanController {

    @Autowired
    PlanService planService;

    @Autowired
    ProductService productService;

    /**
     * 插入问诊方案
     * @param plan
     * @return
     */
    @PostMapping(value = "/insertPlan")
    @UserLoginToken
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public ApiResult insertPlan(@RequestBody Plan plan) {
        log.info("请求参数为：" +plan.toString());
        planService.insert(plan);

        List<Ear> earList = plan.getEarList();
        if (!earList.isEmpty()) {
            for (Ear ear :earList) {
                ear.setPlanid(plan.getId());
            }
            planService.insertEar(earList);
        }

        List<Discomfort> discomfortList = plan.getDiscomfortList();
        if (!discomfortList.isEmpty()) {
            for (Discomfort discomfort:discomfortList) {
                discomfort.setPlanid(plan.getId());
            }
            planService.insertDiscomfort(discomfortList);
        }

        List<Conditioning> conditioningList = plan.getConditioningList();
        if (!conditioningList.isEmpty()) {
            for (Conditioning conditioning : conditioningList) {
                conditioning.setPlanid(plan.getId());
                conditioning.setTag(0);//0未成交
            }
            planService.insertConditioning(conditioningList);
        }



        return ApiResult.success();
    }

    /**
     * 方案列表
     * @param cid 顾客id
     * @return
     */
    @GetMapping("/findPlan")
    @UserLoginToken
    public ApiResult findPlan(@RequestParam Integer cid) {
        log.info("请求参数为：顾客id："+cid);
        List<Plan> planList = planService.findPlanByCid(cid);
        //List list1 = new  ArrayList <>();
        for (Plan plan:planList) {
            List<Conditioning> conditioningList= planService.findConditioningByPlanid(plan.getId());
//            for (Conditioning conditioning: conditioningList) {
//                String insidename = productService.findNameById(conditioning.getInside());
//                String insidename_remark = productService.findRemarksById(conditioning.getInside());
//                String othername = productService.findNameById(conditioning.getOther());
//                conditioning.setInsidename(insidename);
//                conditioning.setOthername(othername);
//                conditioning.setInsidename_remark(insidename_remark);
//            }

            List<Discomfort> discomfortList = planService.findDiscomfortByPlanid(plan.getId());
            List<Ear> earList = planService.findEarByPlanid(plan.getId());
            plan.setEarList(earList);
            plan.setConditioningList(conditioningList);
            plan.setDiscomfortList(discomfortList);
        }
        return ApiResult.success(planList);
    }

    /**
     * 更新备注和时长
     * @param plan
     * @return
     */
    @PostMapping("updateRemakeAndTimeById")
    @UserLoginToken
    public ApiResult updateRemakeAndTimeById(@RequestBody Plan plan) {
        log.info("请求参数为：" +plan.toString());
        List<Conditioning> conditioningList = plan.getConditioningList();
        planService.updateRemakeById(plan.getId(), plan.getRemark());
        for (Conditioning conditioning:conditioningList) {
            planService.updateTimeById(conditioning.getId(),conditioning.getStartTime(),conditioning.getEndTime());
        }
        return ApiResult.success();
    }
}
