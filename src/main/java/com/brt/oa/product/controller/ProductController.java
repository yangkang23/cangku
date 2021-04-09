package com.brt.oa.product.controller;

import com.auth0.jwt.JWT;
import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.product.pojo.Product;
import com.brt.oa.product.service.ProductService;
import com.brt.oa.user.service.UserService;
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

    @Autowired
    UserService userService;


    /**
     * 录入产品库存信息
     * @param product
     * @param Authorization
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/insertProduct")
    public ApiResult insertProduct(@RequestBody Product product ,
                                   @RequestHeader String Authorization){
        String token = Authorization.replaceAll("Bearer ", "");
        Integer storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();
        if (StringUtils.isBlank(product.getProduct_name())){
            return ApiResult.error("产品名字不能为空");
        }

        //校验用户名中是否含有空格
        String s = product.getProduct_name().replaceAll(" ", "");
        if (!s.equals(product.getProduct_name())) {
            return ApiResult.error("产品名中含有空格");
        }

        if(productService.findProductByName(product.getProduct_name(),storeid) != 0){
            return ApiResult.error("该产品已存在");
        }
        product.setStoreid(storeid);
        productService.insertProduct(product);
        return ApiResult.success();
    }

    /**
     * 查询库存
     * @param storeid 管理员传 普通用户不传
     * @param Authorization
     * @return
     */
    @UserLoginToken
    @GetMapping(value = "findProductByStoreid")
    public  ApiResult findProduct(@RequestParam Integer storeid,
                                  @RequestHeader String Authorization){
        String token = Authorization.replaceAll("Bearer ", "");
        if (userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getJurisdiction() == 2) {
            storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();
        }
        List<Product> list = productService.findProductByStoreid(storeid);
        return  ApiResult.success(list);
    }

    /**
     * 增加库存
     * @param product_name
     * @param amount
     * @param Authorization
     * @return
     */
    @UserLoginToken
    @GetMapping(value = "addInventory")
    public ApiResult addInventory(@RequestParam String product_name,
                                  @RequestParam Integer amount,
                                  @RequestHeader String Authorization
                                  ){
        String token = Authorization.replaceAll("Bearer ", "");
        Integer storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();
        if (productService.findProductByName(product_name,storeid) != 1){
            return  ApiResult.error("产品不存在");
        }

        productService.addInventory(product_name,amount,storeid);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        logger.warn("在"+date+"增加了"+product_name+"库存"+amount+"个");

        return ApiResult.success();
    }


}
