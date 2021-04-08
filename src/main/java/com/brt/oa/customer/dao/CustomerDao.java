package com.brt.oa.customer.dao;

import com.brt.oa.customer.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerDao {
    void createCustomerInfo(Customer customer);

    List<Customer> findCustomer(@Param("customer_name") String customer_name, @Param("storeid") Integer storeid);

    void updateDeal(@Param("cid") Integer cid, @Param("deal") String deal);

    List<Customer> findAllCustomer(@Param("customer_name") String customer_name, @Param("storeid") Integer storeid);

    Integer findTotal(@Param(value = "storeid") Integer storeid);

    List<String> findChannelList(@Param(value = "storeid") Integer storeid);

    Integer findChannelAmount(@Param("s") String s, @Param(value = "storeid") Integer storeid);
}
