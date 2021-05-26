package com.brt.oa.customer.controller;

import com.auth0.jwt.JWT;
import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.channel.service.ChannelService;
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
import java.io.UnsupportedEncodingException;
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

    @Autowired
    private ChannelService channelService;

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
        logger.info("请求参数为：" + customer.toString());
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
                                     @RequestParam(required = false) Integer pageSize) throws UnsupportedEncodingException {
        logger.info("请求参数为：门店id" + storeid+"顾客姓名"+customer_name);
        if (pageIndex == null || pageSize == null) {
            pageIndex = 1;
            pageSize = 20;
        }
        if (customer_name != null) {
            customer_name = java.net.URLDecoder.decode(customer_name, "UTF-8");
        }
        // List list1 = new ArrayList();
        List<Customer> list = customerService.findAllCustomer(customer_name, storeid, pageIndex, pageSize);
        Integer total = customerService.findTotals(storeid, customer_name);
        for (Customer customer : list) {
            String store_name = storeService.findNameById(customer.getStoreid());
            customer.setChannelname(channelService.findNameById(customer.getChannelid()));
            customer.setStore_name(store_name);

        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
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
        logger.info("请求参数为：" + storeid);
        Integer toatal = customerService.findTotal(storeid);
        List<Integer> list = customerService.findChannelList(storeid);
        List<String> nameList = new ArrayList();
        List<Integer> amountList = new ArrayList();
        if (list.size() != 0) {
            for (Integer channelid : list) {
                if (channelid != null) {
                    Integer amount = customerService.findChannelAmount(channelid, storeid);
                    amountList.add(amount);
                    String name = channelService.findNameById(channelid);
                    nameList.add(name);
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
        logger.info("请求参数为：" + storeid);
        List<Integer> list = customerService.findChannelList(storeid);
        List<Integer> totalList = new ArrayList<Integer>();
        List<Integer> dealList = new ArrayList<Integer>();
        List<String> nameList = new ArrayList();
        if (list.size() != 0) {
            for (Integer channelid : list) {
                if (channelid != null) {
                    String name = channelService.findNameById(channelid);
                    nameList.add(name);
                    Integer total = customerService.findChannelAmount(channelid, storeid);
                    totalList.add(total);
                    Integer dealamount = customerService.findChannelDealAmount(channelid, storeid);
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
        logger.info("请求参数为：" + customer.toString());
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
        logger.info("请求参数为：" + id);
        Integer state = 0;
        customerService.deleteCustomerById(id, state);
        return ApiResult.success();
    }


}
