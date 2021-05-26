package com.brt.oa.plan.pojo;

import io.github.yedaxia.apidocs.Ignore;
import lombok.Data;

@Data
public class Ear {
    @Ignore
    private Integer id;

    private String deaf;//耳聋

    private String deaf_time;//耳聋时长

    private String tinnitus;//耳鸣

    private String tinnitus_time;//耳鸣时长

    private String other;//其他

    private String other_time;//其他时长

    @Ignore
    private Integer planid;//外键
}
