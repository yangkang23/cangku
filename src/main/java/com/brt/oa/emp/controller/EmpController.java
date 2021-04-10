package com.brt.oa.emp.controller;

import com.auth0.jwt.JWT;
import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.emp.pojo.Emp;
import com.brt.oa.emp.service.EmpService;
import com.brt.oa.user.service.UserService;
import com.brt.oa.utils.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 员工接口
 */
@RestController
@RequestMapping("/emp")
public class EmpController {
    private static Logger logger = LoggerFactory.getLogger(EmpController.class);

    @Autowired
    EmpService empService;

    @Autowired
    UserService userService;

    /**
     * 员工信息录入
     * @param emp
     * @return
     */
    @UserLoginToken
    @PostMapping("/insertemp")
    public ApiResult insertemp(@RequestBody @Valid Emp emp ,
                               @RequestHeader String Authorization) {
        System.out.println(emp);
        String token = Authorization.replaceAll("Bearer ", "");
        Integer storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();
        emp.setStoreid(storeid);
        empService.insertemp(emp);
        return ApiResult.success();
    }

    /**
     * 查询员工
     * @param storeid  管理员传  普通用户传0
     * @return
     */
    @UserLoginToken
    @GetMapping("/findemp")
    public ApiResult findemp(@RequestParam Integer storeid,
                             @RequestHeader String Authorization) {
        String token = Authorization.replaceAll("Bearer ", "");
        if (userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getJurisdiction() == 2) {
            storeid = userService.findUserByUsername(JWT.decode(token).getAudience().get(0)).getStoreid();
        }
        System.out.println(storeid);
        List<Emp> list = empService.findemp(storeid);
        return ApiResult.success(list);
    }
}
