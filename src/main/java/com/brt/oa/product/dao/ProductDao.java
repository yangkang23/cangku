package com.brt.oa.product.dao;

import com.brt.oa.product.pojo.Product;
import com.brt.oa.product.pojo.ProductList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductDao {
    void insertProduct(Product product);

    Integer findProductByName(@Param("product_name") String product_name,
                              @Param("storeid") Integer storeid);

    List<Product> findProductByStoreid(Integer storeid);

    void addInventory(@Param("product_name") String product_name,
                      @Param("amount") Integer amount,
                      @Param("storeid") Integer storeid);

    void reduceInventory(@Param("product_name") String product_name,
                         @Param("amount") Integer amount);

    List<Product> findProduct(Integer sotreid);

    Integer findProductInventory(String product_name);

    void insertList(@Param("pojos") List<ProductList> pojo);

    List<ProductList> findProductList(Integer rid);
}
