package com.brt.oa.product.service.impl;

import com.brt.oa.product.dao.ProductDao;
import com.brt.oa.product.pojo.Product;
import com.brt.oa.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;


    @Override
    public void insertProduct(Product product) {
        productDao.insertProduct(product);
    }

    @Override
    public Integer findProductByName(String product_name) {
        return productDao.findProductByName(product_name);
    }

    @Override
    public List<Product> findProductByStoreid(Integer storeid) {
        return productDao.findProductByStoreid(storeid);
    }

    @Override
    public void addInventory(String product_name,Integer amount) {
        productDao.addInventory(product_name,amount);
    }

    @Override
    public void reduceInventory(String product_name, Integer amount) {
        productDao.reduceInventory(product_name,amount);
    }

    @Override
    public List<Product> findProduct(Integer sotreid) {
        return productDao.findProduct(sotreid);
    }

    @Override
    public Integer findProductInventory(String product_name) {
        return productDao.findProductInventory(product_name);
    }
}
