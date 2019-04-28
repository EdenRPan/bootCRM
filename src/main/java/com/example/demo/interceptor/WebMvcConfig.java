package com.example.demo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Eden
 * @Date: 2019/4/18 9:42
 * @Version 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    注册拦截器

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPattern后跟拦截地址，excludePathPatterns后跟排除拦截地址
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login").excludePathPatterns("/tologin");
    }
}
