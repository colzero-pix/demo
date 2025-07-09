package com.jie.service;

import com.jie.exception.UnauthorizedAccessException;
import com.jie.model.dto.LoginDTO;
import com.jie.model.dto.LoginResponseDTO;
import com.jie.model.entity.User;
import com.jie.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthService(
                        AuthenticationManager authenticationManager,
                        JwtUtil jwtUtil,
                        UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    public LoginResponseDTO authenticateUser(LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userService.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new UnauthorizedAccessException("用户未找到"));

        String jwt = jwtUtil.generateToken(loginDTO.getUsername());

        return new LoginResponseDTO(jwt, "登录成功", user.getUsername(), user.getUserId());

        }catch (BadCredentialsException e) {
            throw new UnauthorizedAccessException("用户名或者密码错误");
        }
    }

}
