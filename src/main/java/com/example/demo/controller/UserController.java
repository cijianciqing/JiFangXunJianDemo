package com.example.demo.controller;


import com.example.demo.beans.User;
import com.example.demo.config.myExceptionHandler.exception.UserNotExistException;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/getUser")
    public ModelAndView getUser(){

        ModelAndView mv = new ModelAndView();
        mv.addObject("user",userService.getUsers());
        mv.setViewName("index");
        return mv;
    }


}