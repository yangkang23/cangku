package com.brt.oa.plan.pojo;

import io.github.yedaxia.apidocs.Ignore;
import lombok.Data;

@Data
public class Conditioning {
    @Ignore
    private  Integer id;

    private Long startTime;

    private Long endTime;

    //进度
    private  String progress;

    //调理方向
    private String direction;

    //外部理疗
    private  String outside;

    //内服调理  产品id
    private String inside;

    //其他调理  产品id
    private String other;

    //费用
    @Ignore
    private Double fee;

    //外键
    @Ignore
    private Integer planid;

//    @Ignore
//    private String insidename;
//
//    @Ignore
//    private  String othername;
//
//    @Ignore
//    private  String insidename_remark;

    @Ignore
    private Integer tag;

    @Ignore
    private Integer rid;


}
