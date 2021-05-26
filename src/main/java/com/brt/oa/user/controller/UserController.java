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

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * 用户接口
 *
 * @author yangkang
 */
@RestController
@CrossOrigin
@RequestMapping("/api/user")
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
     * @param user
     * @throws IOException
     */
    @UserLoginToken
    @PostMapping(value = "/register")
    public ApiResult register(@RequestBody User user,
                              @RequestHeader String Authorization) throws IOException {
        logger.info("请求参数为：" + user.toString());

        String token = Authorization.replaceAll("Bearer ", "");
        if (us.findUserByUsername(JWT.decode(token).getAudience().get(0)).getJurisdiction() == 2) {
            return ApiResult.error("您没有权限创建用户");
        }

        //校验用户是否存在
        if (us.duplicateChecking(user.getUsername()) != 0) {
            return ApiResult.error("用户已存在");
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


        //插入用户
        user.setState(1);
        us.insertUser(user);

        return ApiResult.success();
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping(value = "/login")
    public ApiResult login(@RequestBody @Valid User user )  {
        logger.info("请求参数为：" +user.toString());
        User userForBase = us.findUserByUsername(user.getUsername());
        if (userForBase == null) {
            return ApiResult.error("用户不存在");
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                return ApiResult.error("密码错误");
            } else {
                String token = tokenService.getToken(userForBase);
                String store_name = storeService.findNameById(userForBase.getStoreid());
                Map<String, Object> map = new HashMap<>(16);
                map.put("token", token);
                map.put("id", userForBase.getId());
                map.put("username", userForBase.getUsername());
                map.put("jurisdiction", userForBase.getJurisdiction());
                map.put("storeid", userForBase.getStoreid());
                map.put("store_name", store_name);
                return ApiResult.success(map);
            }
        }

    }

    /**
     * 修改密码
     * @param user
     * @throws IOException
     */
    @UserLoginToken
    @PostMapping(value = "/updatePwd")
    public ApiResult updatePwd(@RequestBody User user, @RequestHeader String Authorization) throws IOException {
        logger.info("请求参数为：" +user.toString());
        String token = Authorization.replaceAll("Bearer ", "");
        User user1 = us.findUserByUsername(JWT.decode(token).getAudience().get(0));
            if (!user.getPassword().matches(pattern)) {
                return ApiResult.error("密码不符合规则");
            }
            user1.setPassword(user.getPassword());
            us.updatePwd(user1);
            return ApiResult.success();


    }

    /**
     * 管理员重置普通用户密码
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/resetPwd")
    @UserLoginToken
    public ApiResult resetPwd(@RequestParam Integer id) throws IOException {
        logger.info("请求参数为：用户id:"+id);
        User user = us.findUserById(id);
        Properties properties = new Properties();
        properties.load(new FileInputStream("config/config.properties"));
        user.setPassword(properties.getProperty("password"));
        us.updatePwd(user);
        return  ApiResult.success();
    }

    /**
     * 用户列表
     * @param realname
     * @return
     */
    @GetMapping("/findUser")
    @UserLoginToken
    public ApiResult findUser(@RequestParam(required = false) String realname,
                              @RequestParam(required = false) Integer pageIndex,
                              @RequestParam(required = false) Integer pageSize){
        logger.info("请求参数为：真实姓名:"+realname);
        if (pageIndex == null || pageSize== null){
            pageIndex = 1;
            pageSize =20;
        }
        Integer total = us.findTotal(realname);
        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
        List<User> list = us.findUser(realname,pageIndex,pageSize);
        for (User user:list) {
            Map<String, Object> map = new HashMap<String, Object>();
            String store_name = storeService.findNameById(user.getStoreid());
            map.put("id",user.getId());
            map.put("username",user.getUsername());
            map.put("jurisdiction", user.getJurisdiction());
            map.put("storeid",user.getStoreid());
            map.put("realname", user.getRealname());
            map.put("store_name",store_name);
            list1.add(map);
        }
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("list",list1);
        map1.put("pageIndex",pageIndex);
        map1.put("pageSize",pageSize);
        map1.put("total", total);
        return ApiResult.success(map1);
    }

    /**
     *编辑用户信息
     * @param user
     * @return
     */
    @PostMapping("/updateUserById")
    @UserLoginToken
    public ApiResult updateUserById(@RequestBody User user){
        logger.info("请求参数为："+user.toString());
        us.updateUserById(user,user.getId());
        return ApiResult.success();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @GetMapping("/deleteUserById")
    @UserLoginToken
    public ApiResult deleteUserById(@RequestParam Integer id) {
        logger.info("请求参数为：用户id:"+id);
        Integer state = 0;
        us.deleteUserById(id,state);
        return  ApiResult.success();
    }


}
