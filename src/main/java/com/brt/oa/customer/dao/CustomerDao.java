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

    List<Customer> findAllCustomer(@Param("customer_name") String customer_name, @Param("storeid") Integer storeid,
                                   @Param("start") Integer start, @Param("size") Integer size);

    Integer findTotal(@Param(value = "storeid") Integer storeid);

    List<Integer> findChannelList(@Param(value = "storeid") Integer storeid);

    Integer findChannelAmount(@Param("channelid") Integer channelid, @Param(value = "storeid") Integer storeid);

    Integer findChannelDealAmount(@Param("channelid") Integer channelid, @Param(value = "storeid") Integer storeid);


    void updateCustomerById(@Param("customer") Customer customer, @Param("id") Integer id);

    void deleteCustomerById(@Param("id") Integer id, @Param("state") Integer state);

    String findNameById(@Param("cid") Integer cid);

    Integer findTotals(@Param("storeid") Integer storeid, @Param("customer_name") String customer_name);

}
