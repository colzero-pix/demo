package com.jie.model.dto;

public class LoginResponseDTO {

    public String token;
    public String message;
    public String username;

    public LoginResponseDTO(String token, String message, String username) {
        this.token = token;
        this.message = message;
        this.username = username;
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
}
