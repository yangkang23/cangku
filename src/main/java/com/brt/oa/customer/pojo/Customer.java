package com.brt.oa.customer.pojo;

import io.github.yedaxia.apidocs.Ignore;
import lombok.Data;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Table
public class Customer {
    @Ignore
    private Integer id;//客户id 不传

    @NotBlank(message = "顾客姓名为空")
    private String customer_name;//客户姓名

    private Integer sex;//客户性别  1 男 0 女


    private Long birthday;//生日

    private String phone;//客户手机

    private Integer channelid;//引流渠道
    @Ignore
    private Integer state;//状态
    @Ignore
    private String deal;//是否成交 成交为1 未成交为0  创建顾客时默认为0 不用传
    @Ignore
    private Integer storeid;//所属门店id  根据登录的用户所属门店id来获取
    @Ignore
    private String store_name;

    @Ignore
    private String channelname;//渠道名字

    private String address;//住址
}
