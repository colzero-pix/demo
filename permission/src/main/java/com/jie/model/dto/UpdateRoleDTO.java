package com.jie.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UpdateRoleDTO {

    @NotBlank(message = "请给出操作：升/降(promotion/demotion)")
    @Pattern(
            regexp = "^(promotion|demotion)$", // (?i) 忽略大小写
            message = "操作必须是 promotion 或 demotion"
    )
    private String operate;

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getOperate() {
        return operate;
    }


}
