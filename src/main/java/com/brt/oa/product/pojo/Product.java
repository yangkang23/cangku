package com.brt.oa.product.pojo;

import io.github.yedaxia.apidocs.Ignore;
import lombok.Data;

@Data
public class Product {

    private Integer id;//产品id

    private String product_name;//产品名字

    private Integer inventory;//产品库存

    private Double price;//产品价格

    private Integer storeid;//产品所属门店id
    @Ignore
    private Integer state; //1为生效 0为删除

    @Ignore
    private String remarks;//备注


    private Integer tag;// 1 内服调理  0 其他调理


}
