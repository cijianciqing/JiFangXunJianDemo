package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        //返回登录页面
        registry.addViewController("/").setViewName("forward:/myIndex.html");
        //为什么/login不好用
        registry.addViewController("/login00").setViewName("login");
        //登录成功后返回页面
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/welcome").setViewName("welcome");


        //显示用户列表
        registry.addViewController("/user/list").setViewName("user/list");
        //返回到用户添加页面
        registry.addViewController("/user/add").setViewName("user/add");
    }

    /* @Bean
     public LocaleResolver localeResolver(){
         return new MyLocaleResolver();
     }
     */
//静态资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler     是指你想在url请求的路径
        //addResourceLocations   是图片存放的真实路径
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img");
    }
}
