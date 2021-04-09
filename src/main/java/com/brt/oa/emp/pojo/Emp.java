package com.brt.oa.emp.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Data
@Component
public class Emp {
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String name;

    private String phone;
    private Long entry_time;
    private String sex;
    private String position_level;
    private Integer storeid;
}
