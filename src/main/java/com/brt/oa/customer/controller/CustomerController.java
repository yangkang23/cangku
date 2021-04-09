package com.brt.oa.customer.controller;

import com.auth0.jwt.JWT;
import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.customer.pojo.Customer;
import com.brt.oa.customer.service.CustomerService;
import com.brt.oa.user.service.UserService;
import com.brt.oa.utils.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 顾客接口
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    /**
     * 创建顾客
     *
     * @param customer
     * @return
     */
    @UserLoginToken
    @PostMapping("/createCustomerInfo")
    public ApiResult createCustomerInfo(@RequestBody Customer customer,
                                        @RequestHeader String Authorization) {
        String token = Authorization.replaceAll("Bearer ", "");
        Integer storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();
        //校验顾客姓名是否为空
        if (StringUtils.isBlank(customer.getCustomer_name())) {
            return ApiResult.error("顾客姓名为空");
        }

        //校验顾客性别否为空
        if (StringUtils.isBlank((customer.getSex()))) {
            return ApiResult.error("顾客性别为空");
        }

        //校验顾客手机号是否为空
        if (StringUtils.isBlank((customer.getPhone()))) {
            return ApiResult.error("顾客手机号为空");
        }

        //校验引流渠道是否为空
        if (StringUtils.isBlank(customer.getDrainage_channel())) {
            return ApiResult.error("引流渠道为空");
        }

        //创建顾客时 默认未成交
        customer.setDeal("0");
        customer.setStoreid(storeid);
        customerService.createCustomerInfo(customer);


        return ApiResult.success("创建成功");


    }

    /**
     * 普通用户查询本门店顾客
     * @param customer_name 不填时为不用姓名搜索
     * @return
     */
    @UserLoginToken
    @GetMapping("/findCustomer")
    public ApiResult findCustomer(@RequestParam String customer_name ,
                                  @RequestHeader String Authorization) {
        String token = Authorization.replaceAll("Bearer ", "");
        Integer storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();
        List<Customer> list = customerService.findCustomer(customer_name, storeid);
        return ApiResult.success(list);
    }

    /**
     * 管理员查询客户
     *
     * @param customer_name 不填时为不用姓名搜索
     * @param storeid 传1时 为全部客户
     * @return
     */
    @UserLoginToken
    @GetMapping("/findAllCustomer")
    public ApiResult findAllCustomer(@RequestParam String customer_name,
                                     @RequestParam Integer storeid) {
        List<Customer> list = customerService.findAllCustomer(customer_name, storeid);
        return ApiResult.success(list);
    }

    /**
     * 查询引流渠道饼状图
     *
     * @param storeid 管理员需传 普通用户不用
     * @param Authorization 获取token中的信息 不用管
     * @return
     */
    @UserLoginToken
    @GetMapping(value = "/findChannel")
    public ApiResult findChannel(@RequestParam Integer storeid,
                                 @RequestHeader String Authorization) {
        String token = Authorization.replaceAll("Bearer ", "");
        if (userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getJurisdiction() == 2) {
            storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();
        }
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
     * @param storeid 管理员需传 普通用户不用
     * @param Authorization 获取token中的信息 不用管
     * @return
     */
    @UserLoginToken
    @GetMapping("/turnoverRate")
    public ApiResult turnoverRate(@RequestParam Integer storeid,
                                  @RequestHeader String Authorization) {
        String token = Authorization.replaceAll("Bearer ", "");
        if (userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getJurisdiction() == 2) {
            storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();
        }
        List<String> nameList = customerService.findChannelList(storeid);
        List<Integer> totalList = new ArrayList<Integer>();
        List<Integer> dealList = new ArrayList<Integer>();
        if (nameList.size() != 0) {
            for (String channelname : nameList) {
                if (channelname != null) {
                   Integer total = customerService.findChannelAmount(channelname,storeid);
                   totalList.add(total);
                   Integer dealamount = customerService.findChannelDealAmount(channelname,storeid);
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

}
