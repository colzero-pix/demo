package com.jie.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class UserInfoDTO {

    @NotBlank(message = "用户ID不可为空")
    private int userId;

    @NotBlank(message = "用户名不可为空")
    private String username;

    private String email;

    private String phone;

    private LocalDateTime gmtCreat;

    public UserInfoDTO(int userId, String username, String email, String phone, LocalDateTime gmtCreat) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.gmtCreat = gmtCreat;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getGmtCreat() {
        return gmtCreat;
    }

    public void setGmtCreat(LocalDateTime gmtCreat) {
        this.gmtCreat = gmtCreat;
    }
}
