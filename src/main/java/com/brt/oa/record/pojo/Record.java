package com.brt.oa.record.pojo;

import com.brt.oa.product.pojo.ProductList;
import io.github.yedaxia.apidocs.Ignore;
import lombok.Data;

import java.util.List;

@Data
public class Record {
    @Ignore
    private  Integer id;//消费记录id

    private  String project_name;//消费项目名字 没有就不传
    private  Double project_cost;//消费项目价格  没有就不传
    private  Double total_cost;//消费总费用  可传 可不传  后端会算
    private  Long deal_date;//消费日期   可传 可不传
    private  Integer cid;//顾客id 通过查询用户所在门店的顾客获得
    private List<ProductList> productLists;//消费产品列表 没有就不传
    @Ignore
    private Double product_fee;//消费产品费用  不用传
    @Ignore
    private Integer storeid;//归属门店 统计营业额要用
    @Ignore
    private String store_name;
    @Ignore
    private  Integer state;
    @Ignore
    private String customer_name;
}
