package com.brt.oa.user.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class User {
    private  Integer id;//用户id
    @NotBlank(message = "用户名为空")
    private  String username;//用户名
    @NotBlank(message = "用户密码为空")
    private  String password; //用户密码

    private  Integer jurisdiction;//用户权限 1为管理员  2为普通用户
    private  Integer storeid;//所属门店  管理员为1

}
