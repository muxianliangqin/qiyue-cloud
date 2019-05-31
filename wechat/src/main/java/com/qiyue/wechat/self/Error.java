package com.qiyue.wechat.self;

public enum Error {
    SUCCESS("0000","success"),

    ENUM_ERROR("0003","枚举名称错误"),
    UNKNOWN_ERROR("9999","未知错误");

    private String code;

    private String msg;

    Error(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
