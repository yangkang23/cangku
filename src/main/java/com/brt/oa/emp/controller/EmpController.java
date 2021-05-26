package com.brt.oa.emp.controller;

import com.auth0.jwt.JWT;
import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.emp.pojo.Emp;
import com.brt.oa.emp.service.EmpService;
import com.brt.oa.store.service.StoreService;
import com.brt.oa.user.service.UserService;
import com.brt.oa.utils.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工接口
 */
@RestController
@CrossOrigin
@RequestMapping("/api/emp")
public class EmpController {
    private static Logger logger = LoggerFactory.getLogger(EmpController.class);

    @Autowired
    EmpService empService;

    @Autowired
    UserService userService;

    @Autowired
    StoreService storeService;

    /**
     * 员工信息录入
     *
     * @param emp
     * @return
     */
    @UserLoginToken
    @PostMapping("/insertemp")
    public ApiResult insertemp(@RequestBody @Valid Emp emp,
                               @RequestHeader String Authorization) {
        logger.info("请求参数为：" + emp.toString());
        String token = Authorization.replaceAll("Bearer ", "");
        Integer storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();
        emp.setStoreid(storeid);
        emp.setState(1);
        empService.insertemp(emp);
        return ApiResult.success();
    }

    /**
     * 查询员工
     *
     * @param storeid 传0为查全部
     * @return
     */
    @UserLoginToken
    @GetMapping("/findemp")
    public ApiResult findemp(@RequestParam Integer storeid,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) Integer pageIndex,
                             @RequestParam(required = false) Integer pageSize) {
        logger.info("请求参数为：门店id:" + storeid+" 员工姓名:"+name);
        if (pageIndex == null || pageSize == null) {
            pageIndex = 1;
            pageSize = 20;
        }
        Integer total = empService.findTotal(storeid, name);
        List list1 = new ArrayList();
        List<Emp> list = empService.findemp(storeid, pageIndex, pageSize, name);
        for (Emp emp : list) {
            String store_name = storeService.findNameById(emp.getStoreid());
            emp.setStore_name(store_name);

        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        map.put("total", total);
        return ApiResult.success(map);
    }

    /**
     * 删除员工
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteEmpById")
    @UserLoginToken
    public ApiResult deleteEmpById(@RequestParam Integer id) {
        logger.info("请求参数为：" + id);
        Integer state = 0;
        empService.deleteEmpById(id, state);
        return ApiResult.success();
    }

    /**
     * 编辑员工信息
     *
     * @param emp
     * @return
     */
    @PostMapping("/updateEmpById")
    @UserLoginToken
    public ApiResult updateEmpById(@RequestBody Emp emp) {
        logger.info("请求参数为：" + emp);
        empService.updateEmpById(emp, emp.getId());
        return ApiResult.success();
    }

}
