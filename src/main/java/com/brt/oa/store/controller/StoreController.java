package com.brt.oa.store.controller;

import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.store.pojo.Store;
import com.brt.oa.store.service.StoreService;
import com.brt.oa.utils.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门店接口
 *
 * @author yangkang
 */
@RestController
@CrossOrigin
@RequestMapping("/api/store")
public class StoreController {
    //日志
    private static Logger logger = LoggerFactory.getLogger(StoreController.class);

    //电话号码正则
//    private String cellphone = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";

    @Autowired
    StoreService storeService;

    /**
     * 门店信息录入
     * @param store
     * @return
     */
    @PostMapping(value = "/insertStore")
    public ApiResult insertStore(@RequestBody @Valid Store store){
        logger.info("请求参数为：" + store.toString());
        if (storeService.findStoreByName(store.getStore_name()) != 0){
            return ApiResult.error("门店名称已存在");
        }

        if (storeService.findStoreByAddress(store.getAddress()) != 0){
            return ApiResult.error("门店地址已存在");
        }
        store.setState(1);
        storeService.insertStore(store);
        return ApiResult.success();
    }

    /**
     * 城市列表
     * @return
     */
    @GetMapping(value = "/findCity")
    public ApiResult findCityList(){

      List list = storeService.findCity();
      return ApiResult.success(list);
    }

    /**
     * 门店列表
     * @param city
     * @param store_name
     * @return
     */
    @GetMapping(value = "/findStore")
    public ApiResult findStore (@RequestParam(required = false) String city,
                                @RequestParam(required = false) String store_name,
                                @RequestParam(required = false) Integer pageIndex,
                                @RequestParam(required = false) Integer pageSize){
        logger.info("请求参数为：城市名" + city+"门店名"+store_name);
        if (pageIndex == null || pageSize== null){
            pageIndex = 1;
            pageSize =20;
        }
        Integer total = storeService.findTotal();
        List list = storeService.findStore(city,store_name,pageIndex,pageSize);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list",list);
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        map.put("total", total);
        return ApiResult.success(map);
    }

    /**
     * 编辑门店信息
     * @param store
     * @return
     */
    @PostMapping("/updateStoreById")
    @UserLoginToken
    public ApiResult updateStoreById(@RequestBody Store store) {
        logger.info("请求参数为：" + store.toString());
        storeService.updateStoreById(store, store.getId());
        return ApiResult.success();
    }

    /**
     * 删除门店
     * @param id
     * @return
     */
    @GetMapping("/deleteStoreById")
    @UserLoginToken
    public ApiResult deleteStoreById(@RequestParam Integer id){
        logger.info("请求参数为：" + id);
        Integer state = 0;
        storeService.deleteStoreById(id,state);
        return ApiResult.success();
    }


}
