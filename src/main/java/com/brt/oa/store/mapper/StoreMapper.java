package com.brt.oa.store.mapper;

import com.brt.oa.store.pojo.Store;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StoreMapper {
    @InsertProvider(type = StoreProvide.class,method = "insertStore")
    void insertStore(Store store);

    @SelectProvider(type = StoreProvide.class,method = "findStoreByName")
    Integer findStoreByName(String name);

    @SelectProvider(type = StoreProvide.class,method = "findStoreByAddress")
    Integer findStoreByAddress(String address);

    @SelectProvider(type = StoreProvide.class , method = "findStoreByCity")
    List<Store> findStoreByCity(String city);

    @SelectProvider(type = StoreProvide.class, method = "findStore")
    List<Store> findStore();

    @SelectProvider(type = StoreProvide.class , method = "findStoreById")
    Integer findStoreById(Integer id);

}
