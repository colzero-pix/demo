package com.jie.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    //1-超管,2-管理员,3-普通用户
    @Id
    @Column(name = "role_id", nullable = false)
    private int roleId;

    //super_admin,admin,user
    @Column(name = "role_code", nullable = false)
    private String role;

    public Role() {

    }

    public Role(int roleId, String role) {

        this.roleId = roleId;
        this.role = role;
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
