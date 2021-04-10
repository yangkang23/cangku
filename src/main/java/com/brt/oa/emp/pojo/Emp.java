package com.brt.oa.emp.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Data
@Component
public class Emp {
    private Integer id;

    @NotBlank(message = "员工姓名为空")
    private String name;

    @NotBlank(message = "手机号为空")
    @Length(min = 11 ,max = 11)
    private String phone;

    private Long entry_time;

    @NotBlank(message = "性别为空")
    private String sex;

    @NotBlank(message = "职位级别为空")
    private String position_level;

    private Integer storeid;
}
