package com.qiyue.crawler.self;

public enum Error {
    SUCCESS("0000","success"),
    /* 自定义错误区 */
    NO_RECORD("0001","记录不存在"),

    /* 系统错误区 */
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
