package com.jie.controller;


import com.jie.model.entity.User;
import com.jie.model.response.UserPN;
import com.jie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    //注册账户
    @PostMapping("/register")
    public String register() {
        return "success1";
    }

    //登录账户
    @PostMapping("/login")
    public String login() {
        return "success2";
    }

    //获取用户信息
    @GetMapping("/{userId}")
    public String getUserInfo() {
        User user = new User();
        user = userRepository.findByUsername("colzero");
//        user.setUsername("jay");
//        user.setPassword("jayPassword");
//        user.setEmail("jay@qq.com");
//        user.setPhone("jayPhoneNumber");
        return user.toString();
    }

    //更新用户信息
    @PutMapping("/{userId}")
    public String updateUserInfo() {
        return "success4";
    }

    //修改用户密码
    @PostMapping("/reset-password")
    public String updatePassword() {
        return "success5";
    }
}
