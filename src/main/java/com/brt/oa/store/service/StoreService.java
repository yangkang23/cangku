package com.brt.oa.store.service;

import com.brt.oa.store.pojo.Store;

import java.util.List;

public interface StoreService {
    void insertStore(Store store);

    Integer findStoreByAddress(String address);

    Integer findStoreById(Integer storeid);

    Integer findStoreByName(String store_name);

    List<String> findCity();

    List<Store> findStore(String city,String storename,Integer pageIndex,Integer pageSize);

    String findNameById(Integer storeid);

    void updateStoreById(Store store, Integer id);

    void deleteStoreById(Integer id ,Integer state);

    Integer findTotal();
}
