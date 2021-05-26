package com.brt.oa.product.dao;

import com.brt.oa.product.pojo.AddRecord;
import com.brt.oa.product.pojo.Product;
import com.brt.oa.product.pojo.ProductList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductDao {
    void insertProduct(Product product);

    Integer findProductById(@Param("id") Integer id);

    List<Product> findProductByStoreid(@Param("storeid") Integer storeid, @Param("start") Integer start,
                                       @Param("size") Integer size, @Param("product_name") String product_name, @Param("tag") Integer tag);

    void addInventory(@Param("id") Integer id,
                      @Param("amount") Integer amount,
                      @Param("storeid") Integer storeid);

    void reduceInventory(@Param("id") Integer id,
                         @Param("amount") Integer amount);

    List<Product> findProduct(Integer sotreid);

    Integer findProductInventory(String product_name);


    List<ProductList> findProductList(Integer rid);

    Integer findProductByName(@Param("product_name") String product_name, @Param("storeid") Integer storeid);

    void updateProductById(@Param("product") Product product, @Param("id") Integer id);

    void insertList(@Param("productLists") List<ProductList> productLists);

    void deleteProductById(@Param("id") Integer id, @Param("state") Integer state);

    Integer findIdByProductNameAndStoreid(@Param("product_name") String product_name, @Param("storeid") Integer storeid);

    void insertAddRecord(@Param("addRecord") AddRecord addRecord);

    List<AddRecord> findAddrecord(@Param("pid") Integer pid, @Param("start") Integer start, @Param("size") Integer size);

    Integer findTotal(@Param("storeid") Integer storeid, @Param("product_name") String product_name, @Param("tag") Integer tag);

    Product findProductByIdA(@Param("id") Integer id);

    List<ProductList> findAmountByRid(@Param("rid") Integer rid);

    void AddInventory(@Param("pid") Integer pid, @Param("amount") Integer amount);

    void deleteProductByRid(@Param("rid") Integer rid, @Param("state") Integer state);

    Integer findTotals(@Param("pid") Integer pid);

    List<Product> findProductByTag(@Param("storeid") Integer storeid, @Param("tag") Integer tag);

    Double findPriceById(Integer id);

    String findNameById(Integer inside);

    String findRemarksById(Integer inside);
}
