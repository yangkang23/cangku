package com.brt.oa.user.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.brt.oa.user.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2020-05-26
 */

@Service("TokenService")
//写一个token的生成方法
public class TokenService {

    private static final long EXPIRE_TIME = 30*60*1000;

    public String getToken(User user) {
        String token="";
        // 存入需要保存在token的信息，这里把用户名存入token
        token= JWT.create().withAudience(user.getUsername())
                //.withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                .sign(Algorithm.HMAC256(user.getPassword()));
        // 使用HMAC256生成token,密钥则是用户的密码
        return token;
    }
}
