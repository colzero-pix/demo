package com.jie.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id", nullable = false)
    private int roleId;

    @Column(name = "role", nullable = false)
    private String role;

    public Role() {

    }

    public Role(int roleId, String role) {
        this.roleId = roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRole() {
        return role;
    }


}
