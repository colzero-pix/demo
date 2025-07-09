package com.jie.service;

import com.jie.exception.ForbiddenException;
import com.jie.exception.UserNotFoundException;
import com.jie.exception.UsernameAlreadyExistsException;
import com.jie.model.dto.ChangePasswordDTO;
import com.jie.model.dto.InfoChangeDTO;
import com.jie.model.dto.UserDTO;
import com.jie.model.dto.UserInfoDTO;
import com.jie.model.entity.User;
import com.jie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
            throw new UsernameAlreadyExistsException("该用户名已经被注册，请更换用户名");
        }

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setGmtCreat(LocalDateTime.now());
        userRepository.save(newUser);

        return newUser;
    }

    public String changePassword(ChangePasswordDTO changePasswordDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("该用户不存在" + username));

        if(!passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("当前密码输入错误");
        }

        if(!changePasswordDTO.getConfirmPassword().equals(changePasswordDTO.getNewPassword())) {
            throw new RuntimeException("新密码和确认密码不相同");
        }

        if(passwordEncoder.matches(changePasswordDTO.getNewPassword(), user.getPassword())) {
            throw new RuntimeException("新密码不能与原密码相同");
        }

        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);

        return "密码修改成功";

    }

    public ResponseEntity<?> getUserInfo(int userId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUsername = authentication.getName();

            User targetUser = userRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException("查询ID不存在：" + userId));

            if(!targetUser.getUsername().equals(currentUsername)) {
                boolean isAdmin = authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

                if(!isAdmin) {
                    throw new ForbiddenException("无权访问该id用户信息");
                }
            }

            UserInfoDTO targetInfo = new UserInfoDTO(targetUser.getUserId(),
                                                    targetUser.getUsername(),
                                                    targetUser.getEmail(),
                                                    targetUser.getPhone(),
                                                    targetUser.getGmtCreat());

            return ResponseEntity.ok(targetInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    public ResponseEntity<?> changeInfo(int userId, InfoChangeDTO infoChangeDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUsername = authentication.getName();

            User targetUser = userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("查询ID不存在：" + userId));

            if(!targetUser.getUsername().equals(currentUsername)) {
                boolean isAdmin = authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

                if(!isAdmin) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("无权修改该ID用户信息");
                }
            }

            targetUser.setEmail(infoChangeDTO.getEmail());
            targetUser.setPhone(infoChangeDTO.getPhone());

            return ResponseEntity.ok(targetUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
}
