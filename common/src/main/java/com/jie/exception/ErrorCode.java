package com.jie.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(404, "用户不存在"),
    PERMISSION_DENIED(403, "权限不足"),
    INVALID_REQUEST(400, "无效请求"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");

    private final int code;
    private final String message;

}
