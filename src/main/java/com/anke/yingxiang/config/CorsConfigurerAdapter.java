package com.anke.yingxiang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by qingxiangzhang on 2017/11/20.
 */
@Component("corsConfigurerAdapter")
public class CorsConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Value("${static.path}")
    private String staticPath;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/index.html");
        super.addViewControllers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(staticPath);
        super.addResourceHandlers(registry);
    }

    /**
     * 跨域处理
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        super.addCorsMappings(registry);

//        registry.addMapping("/wx/test").allowedOrigins("http:anke.inzhi.cn:8080").allowCredentials(false);

        registry.addMapping("/**").allowedOrigins("*");
//        registry.addMapping("/anke/*").allowedOrigins("*");
//        registry.addMapping("/task/*").allowedOrigins("*");
//        registry.addMapping("/task/*/*").allowedOrigins("*");
//        registry.addMapping("/task/tasks/*").allowedOrigins("*");
//        registry.addMapping("/wx/*").allowedOrigins("*");
//        registry.addMapping("/wx/*?*").allowedOrigins("*");
//        registry.addMapping("/img/*").allowedOrigins("*");
//        registry.addMapping("/store/*").allowedOrigins("*");
//        registry.addMapping("/store/*/*").allowedOrigins("*");
    }
}
