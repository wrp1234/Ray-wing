package com.wrp.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wrp
 * @since 2025年07月10日 14:07
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // org.springframework.web.servlet.resource.NoResourceFoundException: No static resource .well-known/appspecific/com.chrome.devtools.json.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 忽略 .well-known 路径的静态资源请求
        registry.addResourceHandler("/.well-known/**", "/appspecific/**")
                .addResourceLocations("classpath:.well-known/", "classpath:appspecific/")
                .resourceChain(false);
    }
}