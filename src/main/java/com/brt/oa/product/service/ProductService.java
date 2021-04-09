package com.brt.oa.product.service;

import com.brt.oa.product.pojo.Product;

import java.util.List;

public interface ProductService {
    public void  insertProduct(Product product);

    Integer findProductByName(String product_name,Integer storeid);

    List<Product> findProductByStoreid(Integer i);

    void addInventory(String product_name,Integer amount,Integer storeid);

    void reduceInventory(String product_name, Integer amount);

    List<Product> findProduct(Integer sotreid);

    Integer findProductInventory(String product_name);

}
