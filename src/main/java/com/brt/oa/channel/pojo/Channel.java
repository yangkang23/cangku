package com.brt.oa.channel.pojo;

import lombok.Data;

@Data
public class Channel {

    private Integer id;

    private String name;//渠道名字

    private Integer tag;//是否常用  1常用  0不常用

    private String describe;//渠道描述
}