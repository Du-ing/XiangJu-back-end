package com.xiangju.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置静态资源URL访问映射
 */
@Configuration
public class ImgUrlConfig implements WebMvcConfigurer {

    @Value("${file-save-path}")
    private String fileSavePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/saveFiles/**")
                .addResourceLocations("file:"+fileSavePath);
    }
}
