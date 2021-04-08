package com.brt.oa.product.dao;

import com.brt.oa.product.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductDao {
    public void insertProduct(Product product);

    Integer findProductByName(String product_name);

    List<Product> findProductByStoreid(Integer storeid);

    void addInventory(@Param("product_name") String product_name,@Param("amount") Integer amount);

    void reduceInventory(@Param("product_name") String product_name,@Param("amount") Integer amount);

    List<Product> findProduct(Integer sotreid);

    Integer findProductInventory(String product_name);
}
