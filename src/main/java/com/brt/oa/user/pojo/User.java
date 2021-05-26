package com.brt.oa.user.pojo;

import io.github.yedaxia.apidocs.Ignore;
import lombok.Data;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Table
public class User {
    @Ignore
    private  Integer id;//用户id
    @NotBlank(message = "用户名为空")
    private  String username;//用户名

    private String realname;//真名
    @Ignore
    private  String password; //用户密码

    private  Integer jurisdiction;//用户权限 1为管理员  2为普通用户 3为医师
    private  Integer storeid;//所属门店  管理员为1
    @Ignore
    private  Integer state;//状态  1为生效 0为删除

    @Ignore
    private  String store_name;

}
