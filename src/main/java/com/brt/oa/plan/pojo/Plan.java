package com.brt.oa.plan.pojo;

import io.github.yedaxia.apidocs.Ignore;
import lombok.Data;

import java.util.List;


@Data
public class Plan {
    @Ignore
    private Integer id;

    //姓名
    private String name;

    //年龄
    private Integer age;

    // 1男 0女
    private  Integer sex;

    //职业
    private  String job;

    //家庭住址
    private  String address;

    //联系方式
    private String phone;

    //耳部不适
    private List<Ear> earList;

    //处置情况
    private String disposal;

    //其他不适
    private List<Discomfort> discomfortList;

    //过敏史 1有 0无
    private  Integer allergy;

    //感冒 1有 0无
    private  Integer cold;

    //不适程度
    private String degree;

    //调理时机
    private  String opportunity;

    //调理周期
    private String cycle;

    //调理方案
    private List<Conditioning> conditioningList;

    //时间
    private Long time;

    // 外键顾客id
    private Integer cid;

    private String remark;

    private String registrant;

}
