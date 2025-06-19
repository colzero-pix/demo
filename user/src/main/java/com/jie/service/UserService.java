package com.jie.service;

import com.jie.model.dto.UserDTO;
import com.jie.model.entity.User;
import com.jie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(UserDTO userDTO) {
        //检查用户名是否已经存在
        if(userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("该用户名已经被注册，请更换用户名");
        }

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(newUser);

        return newUser;
    }
}
