package com.brt.oa.emp.pojo;

import io.github.yedaxia.apidocs.Ignore;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Data
@Component
public class Emp {
    private Integer id;

    @NotBlank(message = "员工姓名为空")
    private String name;//员工姓名


    private String phone;//手机号

    private Long entry_time;//入职时间

    private Integer sex;//性别 1男 0女

    private String position_level;//职级

    private Integer storeid;//所属门店
    @Ignore
    private Integer state;
    @Ignore
    private String store_name;

    private Long birthday;//生日

    private String hometown;//籍贯
}
