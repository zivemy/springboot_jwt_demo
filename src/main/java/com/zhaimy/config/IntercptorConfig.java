package com.zhaimy.config;

import com.zhaimy.interceptors.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Intercptor的配置类
 */
@Configuration
public class IntercptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/user/test")    //其他接口保护
                .excludePathPatterns("/user/login");//用户接口放行
    }
}
