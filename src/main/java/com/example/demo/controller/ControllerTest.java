package com.example.demo.controller;

import com.example.demo.beans.User;
import com.example.demo.config.myExceptionHandler.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerTest {
    @ResponseBody
    @GetMapping("/test01")
    public String controller01(){

        return "hi wqn";
    }

    @GetMapping("/test02")
    public String controller02(){

        return "aaa";
    }

    @GetMapping("/testException")
    public User getInfo() {
//		public User getInfo(@ApiParam("用户id") @PathVariable String id) {
        throw new UserNotExistException("222");


    }
}
