package com.jie.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "role_id", nullable = false)
    private int roleId;

    public UserRole() {

    }

    public UserRole(int id, int userId, int roleId) {

        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public long getUserId() {

        return userId;
    }

    public void setRoleId(int roleId) {

        this.roleId = roleId;
    }

    public int getRoleId() {

        return roleId;
    }
}
