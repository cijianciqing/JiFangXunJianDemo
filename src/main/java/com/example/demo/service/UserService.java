package com.example.demo.service;

import com.example.demo.beans.User;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<User> getUsers(){
        return userDao.findAll();
    }

    public User addUser(User user){
        return userDao.save(user);
    }
}
