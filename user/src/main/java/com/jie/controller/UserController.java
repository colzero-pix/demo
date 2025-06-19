package com.jie.controller;


import com.jie.model.dto.UserDTO;
import com.jie.model.entity.User;
import com.jie.repository.UserRepository;
import com.jie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    //注册账户
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            User newUser = userService.registerNewUser(userDTO);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //登录账户
    @PostMapping("/login")
    public String login() {
        return "success2";
    }

    //获取用户信息
    @GetMapping("/{userId}")
    public String getUserInfo(@PathVariable(name = "userId") int userId) {
        User user = new User();
        user = userRepository.findByUserId(userId);
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
