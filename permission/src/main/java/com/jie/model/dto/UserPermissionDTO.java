package com.jie.model.dto;

public class UserPermissionDTO {

    private int userId;

    private String username;


    public UserPermissionDTO() {

    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
