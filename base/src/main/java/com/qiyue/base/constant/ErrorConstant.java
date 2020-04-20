package com.qiyue.base.constant;

/**
 * 本常量类中每一个变量都与Error枚举一一对应，以便于错误码与错误信息的管理
 */
public class ErrorConstant {

    public static final String SUCCESS = "SUCCESS";
    /* 自定义错误区 */
    public static final String LOGIN_ERROR = "LOGIN_ERROR";
    // 重复登录
    public static final String LOGIN_MULTI_ERROR = "LOGIN_MULTI_ERROR";
    // token错误
    public static final String TOKEN_ERROR = "TOKEN_ERROR";
    // 记录不存在
    public static final String NO_RECORD = "NO_RECORD";
    // 批量插入失败
    public static final String INSERT_BATCH_ERROR = "INSERT_BATCH_ERROR";
    // 批量插入参数错误
    public static final String INSERT_EMPTY_INPUT_ERROR = "INSERT_EMPTY_INPUT_ERROR";
    // 文件不存在
    public static final String FILE_NOT_FOUND = "FILE_NOT_FOUND";

    /* 系统错误区 */
    // 枚举值不存在
    public static final String ENUM_ERROR = "ENUM_ERROR";
    // 位置错误
    public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";
}
