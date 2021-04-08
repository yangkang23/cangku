package com.brt.oa.user.pojo;

import lombok.Data;

@Data
public class User {
    private  Integer id;//用户id
    private  String username;//用户名
    private  String password; //用户密码
    private  String repassword;//第二次密码
    private  Integer jurisdiction;//用户权限 1为管理员  2为普通用户
    private  Integer storeid;//所属门店  管理员不用传

}
