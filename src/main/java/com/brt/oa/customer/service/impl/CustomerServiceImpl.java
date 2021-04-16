package com.brt.oa.customer.service.impl;

import com.brt.oa.customer.dao.CustomerDao;
import com.brt.oa.customer.pojo.Customer;
import com.brt.oa.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    public CustomerDao customerDao;

    @Override
    public void createCustomerInfo(Customer customer) {
        customerDao.createCustomerInfo(customer);
    }

    @Override
    public List<Customer> findCustomer(String customer_name, Integer storeid) {
        return customerDao.findCustomer(customer_name, storeid);
    }

    @Override
    public void updateDeal(Integer cid, String deal) {
        customerDao.updateDeal(cid, deal);
    }

    @Override
    public List<Customer> findAllCustomer(String customer_name, Integer storeid,Integer pageIndex,Integer pageSize) {

            Integer start = (pageIndex-1)*pageSize;
            Integer size = pageSize;

        return customerDao.findAllCustomer(customer_name, storeid,start,size);
    }

    @Override
    public Integer findTotal(Integer storeid) {
        return customerDao.findTotal(storeid);
    }

    @Override
    public List findChannelList(Integer storeid) {
        return customerDao.findChannelList(storeid);
    }

    @Override
    public Integer findChannelAmount(String channelname, Integer storeid) {
        return customerDao.findChannelAmount(channelname, storeid);
    }

    @Override
    public Integer findChannelDealAmount(String channelname, Integer storeid) {
        return customerDao.findChannelDealAmount(channelname,storeid);
    }

    @Override
    public void updateCustomerById(Customer customer,Integer id) {
        customerDao.updateCustomerById(customer,id);
    }

    @Override
    public void deleteCustomerById(Integer id, Integer state) {
        customerDao.deleteCustomerById(id,state);
    }




}
