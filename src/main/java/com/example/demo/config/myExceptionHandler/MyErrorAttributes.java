package com.example.demo.config.myExceptionHandler;


import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    //返回的值map就是页面和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

        Map<String, Object> map =super.getErrorAttributes(webRequest,includeStackTrace);

        map.put("user","user...");

        //自己的异常处理器携带的数据
        //0 是request中
        //1 是session中
        Map<String, Object> exc = (Map<String, Object>) webRequest.getAttribute("exc", 0);

        map.put("exc",exc);
        return map;
    }
}
