package com.jie.model.dto;

public class LoginResponseDTO {

    public String token;
    public String message;
    public String username;
    public int userId;

    public LoginResponseDTO(String token, String message, String username, int userId) {
        this.token = token;
        this.message = message;
        this.username = username;
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getToken() {
        return this.token;
    }

    public String getMessage() {
        return this.message;
    }

    public int getUserId() {
        return this.userId;
    }
}
