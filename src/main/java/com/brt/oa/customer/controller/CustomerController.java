package com.brt.oa.customer.controller;

import com.auth0.jwt.JWT;
import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.customer.pojo.Customer;
import com.brt.oa.customer.service.CustomerService;
import com.brt.oa.store.service.StoreService;
import com.brt.oa.user.service.UserService;
import com.brt.oa.utils.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 顾客接口
 */
@RestController
@CrossOrigin
@RequestMapping("/api/customer")
public class CustomerController {

    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    /**
     * 创建顾客
     *
     * @param customer
     * @return
     */
    @UserLoginToken
    @PostMapping("/createCustomerInfo")
    public ApiResult createCustomerInfo(@RequestBody @Valid Customer customer,
                                        @RequestHeader String Authorization) {
        String token = Authorization.replaceAll("Bearer ", "");
        Integer storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();

        //创建顾客时 默认未成交
        customer.setDeal("0");
        customer.setState(1); //1是生效 0是表示删除
        customer.setStoreid(storeid);
        customerService.createCustomerInfo(customer);


        return ApiResult.success("创建成功");


    }


    /**
     * 查询客户
     *
     * @param customer_name 不填时为不用姓名搜索
     * @param storeid       传0为查全部
     * @return
     */
    @UserLoginToken
    @GetMapping("/findAllCustomer")
    public ApiResult findAllCustomer(@RequestParam(required = false) String customer_name,
                                     @RequestParam Integer storeid,
                                     @RequestParam(required = false) Integer pageIndex,
                                     @RequestParam(required = false) Integer pageSize) {
       if (pageIndex == null || pageSize== null){
           pageIndex = 1;
           pageSize =20;
       }

        // List list1 = new ArrayList();
        List<Customer> list = customerService.findAllCustomer(customer_name, storeid,pageIndex,pageSize);
        Integer total = customerService.findTotal(storeid);
        for (Customer customer : list) {
            String store_name = storeService.findNameById(customer.getStoreid());
            customer.setStore_name(store_name);

        }
        Map<String,Object> map = new  HashMap<String,Object>();
        map.put("list",list);
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        map.put("total", total);

        return ApiResult.success(map);
    }

    /**
     * 查询引流渠道饼状图
     *
     * @param storeid 传0为查全部
     * @return
     */
    @UserLoginToken
    @GetMapping(value = "/findChannel")
    public ApiResult findChannel(@RequestParam Integer storeid) {
        Integer toatal = customerService.findTotal(storeid);
        List<String> nameList = customerService.findChannelList(storeid);
        List<Integer> amountList = new ArrayList();
        if (nameList.size() != 0) {
            for (String channelname : nameList) {
                if (channelname != null) {
                    Integer amount = customerService.findChannelAmount(channelname, storeid);
                    amountList.add(amount);
                }
            }
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("total", toatal);
        map.put("nameList", nameList);
        map.put("amountList", amountList);
        return ApiResult.success(map);
    }

    /**
     * 查询引流渠道成交率
     *
     * @param storeid 传0为查全部
     * @return
     */
    @UserLoginToken
    @GetMapping("/turnoverRate")
    public ApiResult turnoverRate(@RequestParam Integer storeid
    ) {
        List<String> nameList = customerService.findChannelList(storeid);
        List<Integer> totalList = new ArrayList<Integer>();
        List<Integer> dealList = new ArrayList<Integer>();
        if (nameList.size() != 0) {
            for (String channelname : nameList) {
                if (channelname != null) {
                    Integer total = customerService.findChannelAmount(channelname, storeid);
                    totalList.add(total);
                    Integer dealamount = customerService.findChannelDealAmount(channelname, storeid);
                    dealList.add(dealamount);
                }
            }
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("totalList", totalList);
        map.put("nameList", nameList);
        map.put("amountList", dealList);
        return ApiResult.success(map);
    }

    /**
     * 编辑顾客信息
     *
     * @param customer
     * @return
     */
    @PostMapping("/updateCustomer")
    @UserLoginToken
    public ApiResult updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomerById(customer, customer.getId());
        return ApiResult.success();
    }

    /**
     * 删除顾客
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteCustomer")
    @UserLoginToken
    public ApiResult deleteCustomer(@RequestParam Integer id) {
        Integer state = 0;
        customerService.deleteCustomerById(id, state);
        return ApiResult.success();
    }

}
