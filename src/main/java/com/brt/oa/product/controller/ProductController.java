package com.brt.oa.product.controller;

import com.auth0.jwt.JWT;
import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.product.pojo.AddRecord;
import com.brt.oa.product.pojo.Product;
import com.brt.oa.product.pojo.ProductList;
import com.brt.oa.product.service.ProductService;
import com.brt.oa.user.service.UserService;
import com.brt.oa.utils.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品接口
 */
@RestController
@CrossOrigin
@RequestMapping("/api/product")
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;


    /**
     * 录入产品库存信息
     * @param product
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/insertProduct")
    public ApiResult insertProduct(@RequestBody @Valid Product product ,
                                   @RequestHeader String Authorization){
        String token = Authorization.replaceAll("Bearer ", "");
        Integer storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();


        //校验产品名中是否含有空格
        String s = product.getProduct_name().replaceAll(" ", "");
        if (!s.equals(product.getProduct_name())) {
            return ApiResult.error("产品名中含有空格");
        }

//        if(productService.findProductByName(product.getProduct_name(),storeid) != 0){
//            return ApiResult.error("该产品已存在");
//        }
        product.setStoreid(storeid);
        product.setInventory(0);
        product.setState(1);
        productService.insertProduct(product);
        return ApiResult.success();
    }

    /**
     * 查询库存
     * @param storeid 传0为查全部
     * @return
     */
    @UserLoginToken
    @GetMapping(value = "findProductByStoreid")
    public  ApiResult findProduct(@RequestParam Integer storeid,
                                  @RequestParam(required = false) Integer pageIndex,
                                  @RequestParam(required = false) Integer pageSize){
        if (pageIndex == null || pageSize== null){
            pageIndex = 1;
            pageSize =20;
        }
        Integer total =  productService.findTotal(storeid);
        List<Product> list = productService.findProductByStoreid(storeid,pageIndex,pageSize);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list",list);
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        map.put("total", total);
        return  ApiResult.success(map);
    }

    /**
     * 增加库存
     * @param productList
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "addInventory")
    public ApiResult addInventory(@RequestBody ProductList productList,
                                  @RequestHeader String Authorization
                                  ){
        String token = Authorization.replaceAll("Bearer ", "");
        Integer storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();
        if (productService.findProductById(productList.getPid()) != 1){
            return  ApiResult.error("产品不存在");
        }

        productService.addInventory(productList.getPid(),productList.getAmount(),storeid);

        String product_name = productService.findProductByIdA(productList.getPid()).getProduct_name();
        AddRecord addRecord = new AddRecord();
        addRecord.setOperator(JWT.decode(token).getAudience().get(0));
        addRecord.setAmount(productList.getAmount());
        addRecord.setDeal_date(new Date().getTime());
        addRecord.setStoreid(storeid);
        addRecord.setPid(productList.getPid());
        addRecord.setProduct_name(product_name);
        productService.insertAddRecord(addRecord);
        //logger.warn("在"+date+"增加了"+productList.getProduct_name()+"库存"+productList.getAmount()+"个");

        return ApiResult.success();
    }

    /**
     * 编辑产品信息
     * @param product  id要传
     * @return
     */
    @PostMapping("/updateProductById")
    @UserLoginToken
    public ApiResult updateProductById(@RequestBody Product product) {
        productService.updateProductById(product, product.getId());
        return ApiResult.success();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @GetMapping("/deleteProductById")
    @UserLoginToken
    public ApiResult deleteProductById(@RequestParam Integer id) {
        Integer state = 0;
        productService.deleteProductById(id, state);
        return ApiResult.success();
    }

    /**
     * 查看增加库存记录
     * @param pid 产品id
     * @return
     */
    @GetMapping("/findAddrecord")
    @UserLoginToken
    public ApiResult findAddrecord(@RequestParam Integer pid,
                                   @RequestParam(required = false) Integer pageIndex,
                                   @RequestParam(required = false) Integer pageSize) {
        if (pageIndex == null || pageSize== null){
            pageIndex = 1;
            pageSize =20;
        }
        Integer toatl = productService.findTotals(pid);

        List<AddRecord> list = productService.findAddrecord(pid,pageIndex,pageSize);
        return ApiResult.success(list);

    }


}
