package com.jie.model.dto;

public class RoleCodeDTO {

    private final int roleCode;

    public static RoleCodeDTO SUPER_ADMIN = new RoleCodeDTO(1);
    public static RoleCodeDTO ADMIN = new RoleCodeDTO(2);
    public static RoleCodeDTO USER = new RoleCodeDTO(3);


    public RoleCodeDTO(int roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        if(this.roleCode == 1) {
            return "SUPER_ADMIN";
        } else if (this.roleCode == 2) {
            return "ADMIN";
        }else{
            return "USER";
        }
    }
}
