package com.jie.controller;


import com.jie.model.dto.LoginDTO;
import com.jie.model.dto.LoginResponseDTO;
import com.jie.model.dto.UserDTO;
import com.jie.model.entity.User;
import com.jie.repository.UserRepository;
import com.jie.service.AuthService;
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

    @Autowired
    private AuthService authService;


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
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        LoginResponseDTO response = authService.authenticateUser(loginDTO);
        return ResponseEntity.ok(response);
    }

    //获取用户信息
    @GetMapping("/{userId}")
    public String getUserInfo(@PathVariable(name = "userId") int userId) throws Exception {
        User user = new User();
        user = userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("未找到ID用户:" + userId));
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
