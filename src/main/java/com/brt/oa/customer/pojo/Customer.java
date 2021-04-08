package com.brt.oa.customer.pojo;

import lombok.Data;

@Data
public class Customer {
    private Integer id;//客户id
    private String customer_name;//客户姓名
    private String sex;//客户性别
    private String phone;//客户手机
    private String drainage_channel;//引流渠道
    private String deal;//是否成交 成交为1 未成交为0  创建顾客时默认为0
    private Integer storeid;//所属门店id  根据登录的用户所属门店id来获取
}
