package com.brt.oa.config;

import com.brt.oa.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author
 * @date 2020-05-26
 */


//在配置类上添加了注解@Configuration
//标明了该类是一个配置类并且会将该类作为一个SpringBean添加到IOC容器内
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    //addPathPatterns方法用于设置拦截器的过滤路径规则
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/api/user/login");
    }
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
