package com.brt.oa.plan.pojo;

import io.github.yedaxia.apidocs.Ignore;
import lombok.Data;

@Data
public class Conditioning {
    @Ignore
    private  Integer id;

    //进度
    private  String progress;

    //调理方向
    private String direction;

    //外部理疗
    private  String outside;

    //内服调理
    private String inside;

    //其他调理
    private String other;

    //外键
    @Ignore
    private Integer planid;
}
