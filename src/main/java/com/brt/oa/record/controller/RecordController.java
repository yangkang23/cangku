package com.brt.oa.record.controller;

import com.auth0.jwt.JWT;
import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.customer.service.CustomerService;
import com.brt.oa.product.pojo.ProductList;
import com.brt.oa.product.service.ProductService;
import com.brt.oa.record.pojo.Record;
import com.brt.oa.record.service.RecordService;
import com.brt.oa.user.service.UserService;
import com.brt.oa.utils.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    UserService userService;

    /**
     * 新增消费记录
     * @param record
     * @return
     */
    @UserLoginToken
    @PostMapping("/insertRecord")
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public ApiResult insertRecord(@RequestBody Record record,
                                  @RequestHeader String Authorization){
        try {
            Double product_fee = 0.0 ;
            List<ProductList> list = record.getProductLists();
            if (list != null) {
                if (!list.isEmpty() ) {
                    for (ProductList productList: list) {
                        Double total = productList.getPrice()*productList.getAmount();
                        System.out.println(total);
                        product_fee = product_fee+total;
                        if ((productService.findProductInventory(productList.getProduct_name()) - productList.getAmount()) < 0) {
                            int i = 1/0;
                            //return ApiResult.error("库存不足");
                        }
                        productService.reduceInventory(productList.getProduct_name(), productList.getAmount());
                    }
                }
            }
            customerService.updateDeal(record.getCid(), "1");
            record.setProduct_fee(product_fee);
            record.setTotal_cost(product_fee+record.getProject_cost());
            record.setDeal_date(new Date().getTime());
            recordService.insertRecord(record);
            if (list != null) {
                if (!list.isEmpty()) {
                    for (ProductList productList:list) {
                        System.out.println(record.getDeal_date());
                        productList.setRid(recordService.findId(record.getCid(),record.getDeal_date()));
                    }
                    productService.insertList(list);
                }
            }


        } catch (Exception e) {
            throw new RuntimeException("库存不足");
        }
        return ApiResult.success();
    }

    /**
     * 查询营业额
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param storeid 门店id  管理要传 普通用户传0
     * @return
     */
    @UserLoginToken
    @GetMapping("/findTurnover")
    public ApiResult findTurnover(@RequestParam Long startTime,
                                  @RequestParam Long endTime,
                                  @RequestParam Integer storeid,
                                  @RequestHeader String Authorization) {
        String token = Authorization.replaceAll("Bearer ", "");
        if (userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getJurisdiction() == 2) {
            storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();
        }
        Integer turnover = recordService.findTurnover(startTime, endTime, storeid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startTime",startTime);
        map.put("endtTime",endTime);
        map.put("turnover",turnover);
        return ApiResult.success(map);
    }

    /**
     *  查询消费记录
     * @param id  顾客id
     * @return
     */
    @UserLoginToken
    @GetMapping("findRecord")
    public ApiResult findRecord(@RequestParam Integer id,
                                @RequestHeader String Authorization) {
        List<Record> list = recordService.findRecord(id);
        Map<String,Object> map = new HashMap<String, Object>();
        List list2 = new ArrayList();
        if (!list.isEmpty()) {
            for (Record record:list) {
                List<ProductList> list1 = productService.findProductList(record.getId());
                record.setProductLists(list1);
                System.out.println(record);

                list2.add(record);
            }
            System.out.println(list2);
        }

        return  ApiResult.success(list2);
    }

}
