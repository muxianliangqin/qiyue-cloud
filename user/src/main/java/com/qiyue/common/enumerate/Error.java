package com.qiyue.common.enumerate;

public enum Error {
    SUCCESS("0000","success"),
    /* 自定义错误区 */
    LOGIN_ERROR("0000","用户名或密码不正确"),
    LOGIN_MULTI_ERROR("0001","同一个浏览器只允许一个用户登录"),

    TOKEN_ERROR("2000","token码错误"),

    NO_RECORD("1000","记录不存在"),
    USER_MENU_INSERT_BATCH_ERROR("1001","user_menu表批量插入失败"),
    USER_MENU_INSERT_EMPTY_INPUT_ERROR("1002","user_menu表批量插入输入参数为空"),
    MENU_LOAN_INSERT_BATCH_ERROR("1003","menu_loan表批量插入失败"),
    MENU_LOAN_INSERT_EMPTY_INPUT_ERROR("1004","menu_loan表批量插入输入参数为空"),

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
