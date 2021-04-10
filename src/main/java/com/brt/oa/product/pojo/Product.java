package com.brt.oa.product.pojo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class Product {
    private Integer id;//产品id
    @NotBlank(message = "产品名为空")
    private String product_name;//产品名字

    @Min(1)
    private Integer inventory;//产品库存

    @Min(0)
    private Double pric;//产品价格
    private Integer storeid;//产品所属门店id
}
