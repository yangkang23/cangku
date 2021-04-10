package com.brt.oa.store.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class Store {
    private Integer id;//id 不传
    @NotBlank(message = "门店所在城市为空")
    private String city;//门店所在城市
    @NotBlank(message = "门店名称为空")
    private String store_name;//门店名称
    @NotBlank(message = "店地址为空")
    private String address;//门店地址
    @NotBlank(message = "门店负责人名字为空")
    private String manager_name;//门店负责人名字
    @NotBlank(message = "电话号码为空")
    @Length(min = 11 ,max = 11)
    private String phone;//电话号码
}
