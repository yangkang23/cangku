package com.brt.oa.customer.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class Customer {
    private Integer id;//客户id 不传

    @NotBlank(message = "顾客姓名为空")
    private String customer_name;//客户姓名

    @NotBlank(message = "用户名不能为空")
    private String sex;//客户性别

    @Min(0)
    @Max(100)
    private Integer age;//客户年龄

    @NotBlank(message = "手机号为空")
    @Length(min = 11 ,max = 11)
    private String phone;//客户手机

    @NotBlank(message = "引流渠道为空")
    private String drainage_channel;//引流渠道


    private String deal;//是否成交 成交为1 未成交为0  创建顾客时默认为0 不用传
    private Integer storeid;//所属门店id  根据登录的用户所属门店id来获取
}
