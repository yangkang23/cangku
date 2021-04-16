package com.brt.oa.product.service.impl;

import com.brt.oa.product.dao.ProductDao;
import com.brt.oa.product.pojo.AddRecord;
import com.brt.oa.product.pojo.Product;
import com.brt.oa.product.pojo.ProductList;
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
    public Integer findProductById(Integer id) {
        return productDao.findProductById(id);
    }

    @Override
    public List<Product> findProductByStoreid(Integer storeid,Integer pageIndex,Integer pageSize) {
        Integer start = (pageIndex-1)*pageSize;
        Integer size = pageSize;
        return productDao.findProductByStoreid(storeid,start,size);
    }

    @Override
    public void addInventory(Integer id,Integer amount,Integer storeid) {
        productDao.addInventory(id,amount,storeid);
    }

    @Override
    public void reduceInventory(Integer id, Integer amount) {
        productDao.reduceInventory(id,amount);
    }

    @Override
    public List<Product> findProduct(Integer sotreid) {
        return productDao.findProduct(sotreid);
    }

    @Override
    public Integer findProductInventory(String product_name) {
        return productDao.findProductInventory(product_name);
    }

    @Override
    public void insertList(List<ProductList> productLists) {
        productDao.insertList(productLists);
    }

    @Override
    public List<ProductList> findProductList(Integer rid) {
        return productDao.findProductList(rid);
    }

    @Override
    public Integer findProductByName(String product_name, Integer storeid) {
        return productDao.findProductByName(product_name,storeid);
    }

    @Override
    public void updateProductById(Product product, Integer id) {
        productDao.updateProductById(product,id);
    }

    @Override
    public void deleteProductById(Integer id, Integer state) {
        productDao.deleteProductById(id, state);
    }

    @Override
    public Integer findIdByProductNameAndStoreid(String product_name, Integer storeid) {
        return productDao.findIdByProductNameAndStoreid(product_name,storeid);
    }

    @Override
    public void insertAddRecord(AddRecord addRecord) {
        productDao.insertAddRecord(addRecord);
    }

    @Override
    public List<AddRecord> findAddrecord(Integer pid,Integer pageIndex,Integer pageSize) {
        Integer start = (pageIndex-1)*pageSize;
        Integer size = pageSize;
        return productDao.findAddrecord(pid,start,size);
    }

    @Override
    public Integer findTotal(Integer storeid) {
        return productDao.findTotal(storeid);
    }

    @Override
    public Product findProductByIdA(Integer id) {
        return productDao.findProductByIdA(id);
    }

    @Override
    public List<ProductList> findAmountByRid(Integer rid) {
        return productDao.findAmountByRid(rid);
    }

    @Override
    public void AddInventory(Integer pid, Integer amount) {
        productDao.AddInventory(pid,amount);
    }

    @Override
    public void deleteProductByRid(Integer rid, Integer state) {
        productDao.deleteProductByRid(rid,state);
    }

    @Override
    public Integer findTotals(Integer pid) {
       return   productDao.findTotals(pid);
    }


}
