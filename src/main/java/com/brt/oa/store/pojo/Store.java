package com.brt.oa.store.pojo;

import lombok.Data;

@Data
public class Store {
    private String city;//门店所在城市
    private String store_name;//门店名称
    private String address;//门店地址
    private String manager_name;//门店负责人名字
    private String phone;//电话号码
}
