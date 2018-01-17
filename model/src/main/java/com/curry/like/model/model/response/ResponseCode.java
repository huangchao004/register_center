package com.curry.like.model.model.response;


public enum  ResponseCode {



     SUCCESS(200,"SUCCESS"),
     USERNAME_EXISTS(10002,"用户名已存在"),
     USER_NOT_EXISTS(10003,"用户名或者密码错误"),
     NECESSARY_PARAMS_EMPTY(10001,"必要参数为空");

     private Integer code;
     private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
