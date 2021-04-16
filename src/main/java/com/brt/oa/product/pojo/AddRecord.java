package com.brt.oa.product.pojo;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "add_record")
public class AddRecord {
    private Integer id;

    private String operator;//操作人姓名

    private Integer amount;//增加数量

    private  Long deal_date;//操作时间

    private  Integer pid;//关联产品id

    private  String product_name;//产品名字

    private Integer storeid;//外键关联门店
}
