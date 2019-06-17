package com.qiyue.user.self;

public enum Error {
    SUCCESS("0000","success"),
    /* 自定义错误区 */
    LOGIN_ERROR("0001","用户名或密码不正确"),
    TOKEN_ERROR("0002","token码错误"),
    NO_RECORD("0003","记录不存在"),
    USER_MENU_INSERT_BATCH_ERROR("0004","user_menu表批量插入失败"),
    USER_MENU_INSERT_EMPTY_INPUT_ERROR("0005","user_menu表批量插入输入参数为空"),
    MENU_LOAN_INSERT_BATCH_ERROR("0006","menu_loan表批量插入失败"),

    /* 系统错误区 */
    ENUM_ERROR("9998","枚举名称错误"),
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
