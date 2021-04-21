package com.brt.oa.plan.pojo;

import io.github.yedaxia.apidocs.Ignore;
import lombok.Data;

@Data
public class Discomfort {
    @Ignore
    private Integer id;

    private String otherDiscomfort;//其他不适

    private String otherDiscomfort_time;//其他不适时长

    //手术史
    private String operation;

    @Ignore
    private Integer planid;//外键
}
