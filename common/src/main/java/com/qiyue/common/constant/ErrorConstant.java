package com.qiyue.common.constant;

/**
 * 本常量类中每一个变量都与Error枚举一一对应，以便于错误码与错误信息的管理
 */
public class ErrorConstant {

    public static final String SUCCESS = "SUCCESS";
    /* 自定义错误区 */
    public static final String LOGIN_ERROR = "LOGIN_ERROR";
    public static final String LOGIN_MULTI_ERROR = "LOGIN_MULTI_ERROR";

    public static final String TOKEN_ERROR = "TOKEN_ERROR";

    public static final String NO_RECORD = "NO_RECORD";
    public static final String INSERT_BATCH_ERROR = "INSERT_BATCH_ERROR";
    public static final String INSERT_EMPTY_INPUT_ERROR = "INSERT_EMPTY_INPUT_ERROR";

    /* 系统错误区 */
    public static final String ENUM_ERROR = "ENUM_ERROR";
    public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";
}
