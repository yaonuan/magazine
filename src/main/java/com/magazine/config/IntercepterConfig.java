package com.magazine.config;

import com.magazine.interceoter.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018/11/27 0027
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/user/api/v1/*/**");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
