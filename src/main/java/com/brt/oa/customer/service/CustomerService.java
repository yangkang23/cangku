package com.brt.oa.customer.service;

import com.brt.oa.customer.pojo.Customer;

import java.util.List;

public interface CustomerService {

    void createCustomerInfo(Customer customer);

    List<Customer> findCustomer(String customer_name, Integer storeid);

    void updateDeal(Integer cid, String deal);

    List<Customer> findAllCustomer(String customer_name, Integer storeid,Integer pageIndex,Integer pageSize);

    Integer findTotal(Integer storeid);

    List findChannelList(Integer storeid);

    Integer findChannelAmount(String channelname, Integer storeid);

    Integer findChannelDealAmount(String channelname, Integer storeid);

    void updateCustomerById(Customer customer,Integer id);

    void deleteCustomerById(Integer id, Integer state);


    String findNameById(Integer cid);

    Integer findTotals(Integer storeid, String customer_name);
}
