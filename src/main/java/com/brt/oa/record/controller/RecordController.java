package com.brt.oa.record.controller;

import com.auth0.jwt.JWT;
import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.customer.service.CustomerService;
import com.brt.oa.product.pojo.Product;
import com.brt.oa.product.pojo.ProductList;
import com.brt.oa.product.service.ProductService;
import com.brt.oa.record.pojo.Record;
import com.brt.oa.record.service.RecordService;
import com.brt.oa.store.service.StoreService;
import com.brt.oa.user.service.UserService;
import com.brt.oa.utils.ApiResult;
import com.brt.oa.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * 消费记录接口
 *
 * @author yangkang
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/record")
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

    @Autowired
    StoreService storeService;

    /**
     * 新增消费记录
     *
     * @param record
     * @return
     */
    @UserLoginToken
    @PostMapping("/insertRecord")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public ApiResult insertRecord(@RequestBody @Valid Record record,
                                  @RequestHeader String Authorization) {
        Integer storeid = 1;
        String token = Authorization.replaceAll("Bearer ", "");
        if (userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getJurisdiction() == 2) {
            storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();
        }
        try {
            Double product_fee = 0.0;
            List<ProductList> list = record.getProductLists();
            if (list != null) {
                if (!list.isEmpty()) {
                    for (ProductList productList : list) {
                        Product product = productService.findProductByIdA(productList.getPid());
                        productList.setProduct_name(product.getProduct_name());
                        productList.setPrice(product.getPrice());
                        productList.setPid(product.getId());
                        productList.setState(1);
                        Double total = product.getPrice() * productList.getAmount();
                        product_fee = product_fee + total;
                        if ((product.getInventory() - productList.getAmount()) < 0) {
                            int i = 1 / 0;
                            //return ApiResult.error("库存不足");
                        }
                        productService.reduceInventory(product.getId(), productList.getAmount());
                    }
                }
            }
            String store_name = storeService.findNameById(storeid);
            customerService.updateDeal(record.getCid(), "1");
            record.setProduct_fee(product_fee);
            record.setTotal_cost(product_fee + record.getProject_cost());
            record.setDeal_date(new Date().getTime());
            record.setStore_name(store_name);
            record.setState(1);
            record.setStoreid(storeid);
            recordService.insertRecord(record);
            if (list != null) {
                if (!list.isEmpty()) {
                    for (ProductList productList : list) {

                        productList.setRid(recordService.findId(record.getCid(), record.getDeal_date()));
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
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param storeid   门店id  传0为查全部
     * @return
     */
    @UserLoginToken
    @GetMapping("/findTurnover")
    public ApiResult findTurnover(@RequestParam(required = false) Long startTime,
                                  @RequestParam(required = false) Long endTime,
                                  @RequestParam Integer storeid) {

        List<Map<String, Object>> list = DateUtil.getDatelist(startTime, endTime);
        List turnoverList = new ArrayList();
        List dateList = new ArrayList();
        for (Map<String, Object> map : list) {

            Double turnover = recordService.findTurnover((Long) map.get("starttime"), (Long) map.get("endtime"), storeid);
            if (turnover == null) {
                turnover = 0.0;
                turnoverList.add(turnover);
                dateList.add(map.get("starttime"));
            } else {
                turnoverList.add(turnover);
                dateList.add(map.get("starttime"));
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dateList", dateList);
        map.put("turnoverList", turnoverList);
        return ApiResult.success(map);
    }

    /**
     * 查询消费记录
     *
     * @param storeid       门店id
     * @param customer_name
     * @return
     */
    @UserLoginToken
    @GetMapping("/findRecord")
    public ApiResult findRecord(@RequestParam Integer storeid,
                                @RequestParam(required = false) String customer_name,
                                @RequestParam(required = false) Integer pageIndex,
                                @RequestParam(required = false) Integer pageSize) {
        if (pageIndex == null || pageSize == null) {
            pageIndex = 1;
            pageSize = 20;
        }
        String store_name = storeService.findNameById(storeid);
        Integer total = recordService.findTotal(storeid);
        List<Record> list = recordService.findRecord(storeid, pageIndex, pageSize);
        List list2 = new ArrayList();
        if (!list.isEmpty()) {
            for (Record record : list) {
                List<ProductList> list1 = productService.findProductList(record.getId());
                record.setProductLists(list1);
                record.setStore_name(store_name);
                list2.add(record);
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list2);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        map.put("total", total);

        return ApiResult.success(map);
    }

    /**
     * 删除消费记录
     * @param id
     * @return
     */
    @GetMapping("/deleteRecordById")
    @UserLoginToken
    public ApiResult deleteRecordById(Integer id) {
        Integer state = 0;
        recordService.deleteRecordById(id,state);
        List<ProductList> list = productService.findAmountByRid(id);
        if (!list.isEmpty()) {
            for (ProductList p: list) {

                productService.AddInventory(p.getPid(),p.getAmount());
            }

        }
        productService.deleteProductByRid(id, state);
        return ApiResult.success();
    }

}
