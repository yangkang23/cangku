package com.brt.oa.store.mapper;

import com.brt.oa.store.pojo.Store;

public class StoreProvide {
    public String insertStore(Store store){
        return "insert into store (city,name,address,manager_name,phone) " +
                "values ('"+store.getCity()+"','"+store.getStore_name()+"','"+store.getAddress()+"','"
                +store.getManager_name()+"','"+store.getPhone()+"')";
    }

    public String findStoreByName(String name){
        return "select count(1) from store where name ='"+name+"';";
    }

    public String findStoreByAddress(String address){
        return "select count(1) from store where address ='"+address+"';";
    }

    public String findStoreByCity(String city){
        return "select * from store where city ='"+city+"';";
    }

    public String findStore(){return "select * from store;";}

    public String findStoreById(Integer id){return "select count(1) from store where id ='"+id+"'";}
}
