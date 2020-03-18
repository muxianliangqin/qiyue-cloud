package com.qiyue.common.enumerate;

import lombok.Data;

/**
 * 本枚举中每一个枚举都与ErrorConstant一一对应，以便于错误码与错误信息的管理
 */
public enum Error {
    SUCCESS("0000", "success"),

    /* 用户相关错误 0000-0999*/
    LOGIN_ERROR("0000", "用户名或密码不正确"),
    LOGIN_MULTI_ERROR("0001", "同一个浏览器只允许一个用户登录"),
    TOKEN_ERROR("0002", "token码错误"),
    /* 用户相关错误 */
    /* 数据相关错误 1000-1999*/
    NO_RECORD("1000", "记录不存在"),
    INSERT_BATCH_ERROR("1001", "批量插入失败"),
    INSERT_EMPTY_INPUT_ERROR("1002", "批量插入输入参数为空"),
    /* 数据相关错误 */

    /* 系统相关错误 9000-9999*/
    ENUM_ERROR("9998", "枚举名称错误"),
    UNKNOWN_ERROR("9999", "未知错误");
    /* 系统相关错误 */

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
