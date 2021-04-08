package com.brt.oa.user.controller;

import com.brt.oa.annotation.PassToken;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 管理员注册
     *
     * @param user
     * @return
     */
    @PassToken
    @PostMapping(value = "/adminRegister")
    public ApiResult insertUser(@RequestBody User user) {


        //校验用户名是否为空
        if (StringUtils.isBlank(user.getUsername())) {
            return ApiResult.error("用户名为空");
        }

        //校验用户名中是否含有空格
        String s = user.getUsername().replaceAll(" ", "");
        if (!s.equals(user.getUsername())) {
            return ApiResult.error("用户名中含有空格");
        }

        //校验用户名是否已经存在
        if (us.duplicateChecking(user.getUsername()) != 0) {
            return ApiResult.error("用户名已存在");
        }

        //校验密码是否符合规则
        if (!user.getPassword().matches(pattern)) {
            return ApiResult.error("用户名不符合规则");
        }

        //校验两次密码是否相同
        if (!StringUtils.equals(user.getPassword(), user.getRepassword())) {
            return ApiResult.error("两次密码不相同");
        }

        //设置权限
        user.setJurisdiction(1);

        //插入用户
        us.insertUser(user);
        return ApiResult.success();
    }

    /**
     * 普通用户注册
     *
     * @param user 用户
     * @return
     * @throws IOException
     */
    @UserLoginToken
    @PostMapping(value = "/register")
    public ApiResult register(@RequestBody User user) throws IOException {
        //校验用户名中是否含有空格
        String s = user.getUsername().replaceAll(" ", "");
        if (!s.equals(user.getUsername())) {
            return ApiResult.error("用户名中含有空格");
        }

        //校验用户名是否为空
        if (StringUtils.isBlank(user.getUsername())) {
            return ApiResult.error("用户名为空");
        }

        //校验用户是否存在
        if (us.duplicateChecking(user.getUsername()) != 0) {
            return ApiResult.error(1, "用户已存在");
        }

        //校验门店是否为空
        if (StringUtils.isBlank(user.getStoreid().toString())) {
            return ApiResult.error("用户所属门店为空");
        }

        //校验门店是否存在
        if (storeService.findStoreById(user.getStoreid()) != 1) {
            return ApiResult.error("用户所属门店不存在");
        }


        //设置初始密码
        Properties properties = new Properties();
        properties.load(new FileInputStream("config/config.properties"));
        user.setPassword(properties.getProperty("password"));

        //设置权限
        user.setJurisdiction(2);

        //插入用户
        us.insertUser(user);

        return ApiResult.success();
    }

    /**
     * 用户登录
     *
     * @param user 用户
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/login")
    public ApiResult login(@RequestBody User user) throws IOException {
        User userForBase = us.findUserByUsername(user.getUsername());
        if (userForBase == null) {
            return ApiResult.error("用户不存在");
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                return ApiResult.error("密码错误");
            } else if (userForBase.getPassword().equals("1234567")) {
                String token = tokenService.getToken(userForBase);
                Map<String, String> map = new HashMap<>(16);
                map.put("token", token);
                return ApiResult.success("登录页面", map);
            } else {
                String token = tokenService.getToken(userForBase);
                Map<String, String> map = new HashMap<>(16);
                map.put("token", token);
                return ApiResult.success(map);
            }
        }

    }

    /**
     * 管理员修改密码,普通用户重置密码
     *
     * @param user
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/updatePwd")
    public ApiResult updatePwd(@RequestBody User user) throws IOException {

        if (us.findUserByUsername(user.getUsername()).getJurisdiction() == 1) {
            //校验密码是否符合规则
            if (!user.getPassword().matches(pattern)) {
                return ApiResult.error("密码不符合规则");
            }

            //校验两次密码是否相同
            if (!StringUtils.equals(user.getPassword(), user.getRepassword())) {
                return ApiResult.error("两次密码不相同");
            }
            us.updatePwd(user);
            return ApiResult.success();
        }

        if (us.findUserByUsername(user.getUsername()).getJurisdiction() == 2) {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/config.properties"));
            user.setPassword(properties.getProperty("password"));
            us.updatePwd(user);
        }

        return ApiResult.success();

    }


}
