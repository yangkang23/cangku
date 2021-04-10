package com.brt.oa.product.pojo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ProductList {
    private Integer id; //主键
    @NotBlank(message = "员工姓名为空")
    private String product_name;//产品名字

    private Double price;//产品价格
   @Min(1)
    private Integer amount;//购买数量
    private Integer rid; //外键 与记录关联
}
