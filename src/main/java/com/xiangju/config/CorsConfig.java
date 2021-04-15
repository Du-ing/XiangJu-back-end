package com.xiangju.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置前后端跨域问题
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    //配置跨域请求方式
    static final String[] methods= new String[] { "GET", "POST", "PUT", "DELETE", "PATCH" };

    //添加跨域映射
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods(methods)
                .maxAge(3600);
    }
}
