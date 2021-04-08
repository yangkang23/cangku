package com.brt.oa.store.controller;

import com.brt.oa.store.pojo.Store;
import com.brt.oa.store.service.StoreService;
import com.brt.oa.utils.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 门店接口
 *
 * @author yangkang
 */
@RestController
@RequestMapping("/store")
public class StoreController {
    //日志
    private static Logger logger = LoggerFactory.getLogger(StoreController.class);

    //电话号码正则
    private String cellphone = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";

    @Autowired
    StoreService storeService;

    /**
     * 门店信息录入
     * @param store
     * @return
     */
    @PostMapping(value = "/insertStore")
    public ApiResult insertStore(@RequestBody Store store){

        //校验门店城市
        if (StringUtils.isBlank(store.getCity())){
            return ApiResult.error("门店城市为空");
        }

        //校验门店名称
        if (StringUtils.isBlank(store.getStore_name())){
            return ApiResult.error("门店名称为空");
        }

        if (storeService.findStoreByName(store.getStore_name()) != 0){
            return ApiResult.error("门店名称已存在");
        }

        //校验门店地址
        if (StringUtils.isBlank(store.getAddress())){
            return ApiResult.error("门店地址为空");
        }

        if (storeService.findStoreByAddress(store.getAddress()) != 0){
            return ApiResult.error("门店地址已存在");
        }

        //校验门店负责人姓名
        if (StringUtils.isBlank(store.getManager_name())){
            return ApiResult.error("门店负责人姓名为空");
        }

        //校验电话号码
        if (!store.getPhone().matches(cellphone) ){
            return ApiResult.error("电话号码不正确");
        }

        storeService.insertStore(store);
        return ApiResult.success();
    }

    /**
     * 按照城市找门店
     * @param city
     * @return
     */
    @GetMapping(value = "/groupByCity")
    public ApiResult groupByCity (@RequestParam String city){

      List list = storeService.findStoreByCity(city);
      return ApiResult.success(list);
    }

    /**
     * 门店列表
     */
    @PostMapping(value = "/findStore")
    public ApiResult findStore (){
        List list = storeService.findStore();
        return ApiResult.success(list);
    }

}
