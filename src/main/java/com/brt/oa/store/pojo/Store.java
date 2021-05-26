package com.brt.oa.store.pojo;

import io.github.yedaxia.apidocs.Ignore;
import lombok.Data;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Table
public class Store {

    private Integer id;//id 不传

    private String city;//门店所在城市
    @NotBlank(message = "门店名称为空")
    private String store_name;//门店名称

    private String address;//门店地址

    private String manager_name;//区域经理

    private String phone;//电话号码

    @Ignore
    private Integer state;

    private String shopowner;//店长

    private String shopowner_phone;//店长手机号
}
