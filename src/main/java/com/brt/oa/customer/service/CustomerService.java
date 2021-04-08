package com.brt.oa.customer.service;

import com.brt.oa.customer.pojo.Customer;

import java.util.List;

public interface CustomerService {

    void createCustomerInfo(Customer customer);

    List<Customer> findCustomer(String customer_name, Integer storeid);

    void updateDeal(Integer cid, String deal);

    List<Customer> findAllCustomer(String customer_name, Integer storeid);

    Integer findTotal(Integer storeid);

    List findChannelList(Integer storeid);

    Integer findChannelAmount(String s, Integer storeid);
}
