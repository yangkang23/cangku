package com.brt.oa.product.service;

import com.brt.oa.product.pojo.AddRecord;
import com.brt.oa.product.pojo.Product;
import com.brt.oa.product.pojo.ProductList;

import java.util.List;

public interface ProductService {
    public void  insertProduct(Product product);

    Integer findProductById(Integer id);

    List<Product> findProductByStoreid(Integer i,Integer pageIndex,Integer pageSize,String product_name,Integer tag);

    void addInventory(Integer id,Integer amount,Integer storeid);

    void reduceInventory(Integer id, Integer amount);

    List<Product> findProduct(Integer sotreid);

    Integer findProductInventory(String product_name);

    void insertList(List<ProductList> productLists);

    List<ProductList> findProductList(Integer rid);

    Integer findProductByName(String product_name, Integer storeid);

    void updateProductById(Product product, Integer id);

    void deleteProductById(Integer id, Integer state);

    Integer findIdByProductNameAndStoreid(String product_name, Integer storeid);

    void insertAddRecord(AddRecord addRecord);

    List<AddRecord> findAddrecord(Integer pid,Integer pageIndex,Integer pageSize);

    Integer findTotal(Integer storeid, String proudct_name,Integer tag);

    Product findProductByIdA(Integer id);

    List<ProductList> findAmountByRid(Integer id);

    void AddInventory(Integer pid, Integer amount);

    void deleteProductByRid(Integer rid, Integer state);

    Integer findTotals(Integer pid);


    List<Product> findProductByTag(Integer storeid, Integer tag);

    Double findPriceById(Integer id);

    String findNameById(Integer inside);

    String findRemarksById(Integer inside);
}
