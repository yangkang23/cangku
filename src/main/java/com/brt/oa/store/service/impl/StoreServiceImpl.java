package com.brt.oa.store.service.impl;

import com.brt.oa.store.dao.StoreDao;
import com.brt.oa.store.pojo.Store;
import com.brt.oa.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreDao storeDao;

    public void insertStore(Store store) {
         storeDao.insertStore(store);
    }

    public Integer findStoreByName(String store_name) {
        return storeDao.findStoreByName(store_name);
    }

    public Integer findStoreByAddress(String address) {
        return storeDao.findStoreByAddress(address);
    }

    public List<String> findCity() {
        return storeDao.findCity();
    }

    public List<Store> findStore(String city, String store_name,Integer pageIndex,Integer pageSize) {
        Integer start = (pageIndex-1)*pageSize;
        Integer size = pageSize;
        return storeDao.findStore(city, store_name,start,size);
    }

    @Override
    public String findNameById(Integer storeid) {
        return storeDao.findNameById(storeid);
    }

    @Override
    public void updateStoreById(Store store, Integer id) {
        storeDao.updateStoreById(store, id);
    }

    public Integer findStoreById(Integer id) {
        return storeDao.findStoreById(id);
    }

    @Override
    public void deleteStoreById(Integer id, Integer state) {
        storeDao.deleteStoreById(id, state);
    }

    @Override
    public Integer findTotal() {
        return storeDao.findTotal();
    }
}
