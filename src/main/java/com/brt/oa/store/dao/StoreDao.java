package com.brt.oa.store.dao;

import com.brt.oa.store.pojo.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreDao {
    void insertStore(@Param("store") Store store);

    Integer findStoreByName(@Param("store_name") String store_name);

    Integer findStoreByAddress(@Param("address") String address);

    List<String> findCity();

    List<Store> findStore(@Param("city") String city, @Param("store_name") String store_name,
                          @Param("start") Integer start, @Param("size") Integer size);

    Integer findStoreById(@Param("id") Integer id);

    String findNameById(@Param("storeid") Integer storeid);

    void updateStoreById(@Param("store") Store store, @Param("id") Integer id);

    void deleteStoreById(@Param("id") Integer id, @Param("state") Integer state);

    Integer findTotal();
}
