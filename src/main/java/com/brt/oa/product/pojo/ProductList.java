package com.brt.oa.product.pojo;

import io.github.yedaxia.apidocs.Ignore;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "product_list")
public class ProductList {
    @Ignore
    private Integer id; //主键
    @Ignore
    private String product_name;//产品名字
    @Ignore
    private Double price;//产品价格

    private Integer amount;//增加数量
    @Ignore
    private Integer rid; //外键 与记录关联
    @Ignore
    private Integer state;

    private Integer pid;//外键 与产品关联
}
