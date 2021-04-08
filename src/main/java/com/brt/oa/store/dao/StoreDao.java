package com.brt.oa.store.dao;

import com.brt.oa.store.mapper.StoreMapper;
import com.brt.oa.store.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreDao {

    @Autowired
    StoreMapper storeMapper;

    public void insertStore(Store store){
        storeMapper.insertStore(store);
    }

    public Integer findStoreByName(String name){
        return storeMapper.findStoreByName(name);
    }

    public Integer findStoreByAddress(String address){
        return storeMapper.findStoreByAddress(address);
    }

    public List<Store> findStoreByCity(String city){
        return storeMapper.findStoreByCity(city);
    }

    public List<Store> findStore(){
        return storeMapper.findStore();
    }

    public Integer findStoreById(Integer id ){
        return  storeMapper.findStoreById(id);
    }
}
