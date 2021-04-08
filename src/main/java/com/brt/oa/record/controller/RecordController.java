package com.brt.oa.record.controller;

import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.customer.service.CustomerService;
import com.brt.oa.product.service.ProductService;
import com.brt.oa.record.pojo.Record;
import com.brt.oa.record.service.RecordService;
import com.brt.oa.utils.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 消费记录接口
 * @author yangkang
 */
@RestController
@RequestMapping(value = "/record")
public class RecordController {

    private static Logger logger = LoggerFactory.getLogger(RecordController.class);

    @Autowired
    RecordService recordService;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    /**
     * 新增消费记录
     * @param record
     * @return
     */
    @UserLoginToken
    @PostMapping("/insertRecord")
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public ApiResult insertRecord(@RequestBody Record record){
        record.setTotal_cost(record.getAmount() * record.getPrice() + record.getProject_cost());
        record.setDeal_date(String.valueOf(new Date().getTime()));
        try {

            recordService.insertRecord(record);
            if ((productService.findProductInventory(record.getProduct_name()) - record.getAmount()) < 0) {
                int i = 1/0;
                //return ApiResult.error("库存不足");
            }
            productService.reduceInventory(record.getProduct_name(), record.getAmount());
            customerService.updateDeal(record.getCid(), "1");

        } catch (Exception e) {
            throw new RuntimeException("库存不足");
        }
        return ApiResult.success();
    }

}
