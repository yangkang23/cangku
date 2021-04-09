package com.brt.oa.record.pojo;

import lombok.Data;

@Data
public class Record {
    private  Integer id;//消费记录id
    private  String product_name;//消费产品名字
    private  Double price;//消费产品价格
    private  Integer amount;//消费产品数量
    private  String project_name;//消费项目名字
    private  Double project_cost;//消费项目价格
    private  Double total_cost;//消费总费用
    private  Long deal_date;//消费日期
    private  Integer cid;//顾客id 通过查询用户所在门店的顾客获得
}
