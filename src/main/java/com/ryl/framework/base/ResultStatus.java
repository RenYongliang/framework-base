package com.ryl.framework.base;

/**
 * @author: ryl
 * @description:
 * @date: 2020-07-16 10:30:20
 */
public enum ResultStatus {

    SUCCESS(200, "成功"),
    FAILURE(500, "失败"),
    USER_NEED_AUTHORITIES(201, "用户未登录"),
    USER_LOGIN_FAILED(202, "用户账号或密码错误"),
    USER_NO_ACCESS(203, "用户无权访问"),
    TOKEN_EXPIRED(204, "token过期"),
    TOKEN_INVALID(207, "token无效"),
    INTERNAL_SERVER_ERROR(501,"内部服务异常"),
    ;


    private Integer code;
    private String message;

    ResultStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
