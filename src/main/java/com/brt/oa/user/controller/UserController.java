package com.brt.oa.user.controller;

import com.auth0.jwt.JWT;
import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.store.service.StoreService;
import com.brt.oa.user.pojo.User;
import com.brt.oa.user.service.UserService;
import com.brt.oa.user.service.impl.TokenService;
import com.brt.oa.utils.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 用户接口
 *
 * @author yangkang
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService us;
    @Autowired
    StoreService storeService;
    @Autowired
    TokenService tokenService;
    private String pattern = "^(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{8,14}$";


    /**
     * 创建用户接口
     * @param username
     * @param storeid
     * @return
     * @throws IOException
     */
    @UserLoginToken
    @PostMapping(value = "/register")
    public ApiResult register(@RequestParam String username,
                              @RequestParam Integer storeid,
                              @RequestHeader String Authorization) throws IOException {

        String token = Authorization.replaceAll("Bearer ", "");
        if (us.findUserByUsername(JWT.decode(token).getAudience().get(0)).getJurisdiction() == 2) {
            return ApiResult.error("您没有权限创建用户");
        }
        //校验用户名中是否含有空格
        String s = username.replaceAll(" ", "");
        if (!s.equals(username)) {
            return ApiResult.error("用户名中含有空格");
        }


        //校验用户是否存在
        if (us.duplicateChecking(username) != 0) {
            return ApiResult.error("用户已存在");
        }

        //校验门店是否为空
        if (StringUtils.isBlank(storeid.toString())) {
            return ApiResult.error("用户所属门店为空");
        }

        //校验门店是否存在
        if (storeService.findStoreById(storeid) != 1) {
            return ApiResult.error("用户所属门店不存在");
        }

        User user = new User();

        //设置初始密码
        Properties properties = new Properties();
        properties.load(new FileInputStream("config/config.properties"));
        user.setPassword(properties.getProperty("password"));

        //设置权限
        if (storeid == 1) {
            user.setJurisdiction(1);
            user.setStoreid(1);
        } else {
            user.setJurisdiction(2);
            user.setStoreid(storeid);
        }
        user.setUsername(username);
        //插入用户
        us.insertUser(user);

        return ApiResult.success();
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/login")
    public ApiResult login(@RequestParam String username,
                           @RequestParam String password) throws IOException {
        User userForBase = us.findUserByUsername(username);
        if (userForBase == null) {
            return ApiResult.error("用户不存在");
        } else {
            if (!userForBase.getPassword().equals(password)) {
                return ApiResult.error("密码错误");
            } else {
                String token = tokenService.getToken(userForBase);
                Map<String, String> map = new HashMap<>(16);
                map.put("token", token);
                return ApiResult.success(map);
            }
        }

    }

    /**
     * 修改密码
     * @param password
     * @param repassword
     * @return
     * @throws IOException
     */
    @UserLoginToken
    @PostMapping(value = "/updatePwd")
    public ApiResult updatePwd(@RequestParam String password ,@RequestParam String repassword, @RequestHeader String Authorization) throws IOException {
        String token = Authorization.replaceAll("Bearer ", "");
        User user = us.findUserByUsername(JWT.decode(token).getAudience().get(0));
        if (user.getJurisdiction() == 1) {
            //校验密码是否符合规则
            if (!password.matches(pattern)) {
                return ApiResult.error("密码不符合规则");
            }

            //校验两次密码是否相同
            if (!StringUtils.equals(password, repassword)) {
                return ApiResult.error("两次密码不相同");
            }
            user.setPassword(password);
            us.updatePwd(user);
            return ApiResult.success();
        }
        return ApiResult.success();

    }

    /**
     * 管理员重置普通用户密码
     * @param username
     * @return
     * @throws IOException
     */
    @PostMapping("/resetPwd")
    @UserLoginToken
    public ApiResult resetPwd(@RequestParam String username) throws IOException {
        User user = us.findUserByUsername(username);
        Properties properties = new Properties();
        properties.load(new FileInputStream("config/config.properties"));
        user.setPassword(properties.getProperty("password"));
        us.updatePwd(user);
        return  ApiResult.success();
    }



}
