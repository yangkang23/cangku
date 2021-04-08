package com.brt.oa.product.controller;

import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.product.pojo.Product;
import com.brt.oa.product.service.ProductService;
import com.brt.oa.utils.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 产品接口
 */
@RestController
@RequestMapping("product")
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    /**
     * 录入产品库存信息
     * @param product
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/insertProduct")
    public ApiResult insertProduct(@RequestBody Product product){

        if (StringUtils.isBlank(product.getProduct_name())){
            return ApiResult.error("产品名字不能为空");
        }

        //校验用户名中是否含有空格
        String s = product.getProduct_name().replaceAll(" ", "");
        if (!s.equals(product.getProduct_name())) {
            return ApiResult.error("产品名中含有空格");
        }

        if(productService.findProductByName(product.getProduct_name()) != 0){
            return ApiResult.error("该产品已存在");
        }
        productService.insertProduct(product);
        return ApiResult.success();
    }

    /**
     * 根据用户所属门店查询该门店的产品列表
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "findProductByStoreid")
    public  ApiResult findProduct(){
        List<Product> list = productService.findProductByStoreid(2);
        return  ApiResult.success(list);
    }

    /**
     * 增加库存
     * @param product_name
     * @param amount
     * @return
     */
    @UserLoginToken
    @GetMapping(value = "addInventory")
    public ApiResult addInventory(@RequestParam String product_name ,@RequestParam Integer amount){
        if (productService.findProductByName(product_name) != 1){
            return  ApiResult.error("产品不存在");
        }



        productService.addInventory(product_name,amount);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        logger.warn("在"+date+"增加了"+product_name+"库存"+amount+"个");

        return ApiResult.success();
    }

    /**
     * 管理员根据传的门店id 查看门店的产品
     * @param sotreid
     * @return
     */
    @UserLoginToken
    @GetMapping(value = "/findProduct")
    public ApiResult findProduct(@RequestParam Integer sotreid){
        List<Product> list = productService.findProduct(sotreid);
        return ApiResult.success();
    }
}
