package com.brt.oa.store.service;

import com.brt.oa.store.dao.StoreDao;
import com.brt.oa.store.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    StoreDao storeDao;

    public void insertStore(Store store){
        storeDao.insertStore(store);
    }

    public Integer findStoreByName(String name){
        return  storeDao.findStoreByName(name);
    }

    public Integer findStoreByAddress(String address){
        return storeDao.findStoreByAddress(address);
    }

    public List<Store> findStoreByCity(String city){
        return storeDao.findStoreByCity(city);
    }

    public List<Store> findStore(){
        return storeDao.findStore();
    }

    public Integer findStoreById(Integer id){return  storeDao.findStoreById(id);}
}
