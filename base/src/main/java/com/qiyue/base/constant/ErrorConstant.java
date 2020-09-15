package com.qiyue.base.constant;

import com.qiyue.base.enums.ErrorEnum;

/**
 * 本常量类中每一个变量都与Error枚举一一对应，以便于错误码与错误信息的管理
 */
public class ErrorConstant {

    public static final ErrorEnum SUCCESS = ErrorEnum.SUCCESS;
    // 登录失败
    public static final ErrorEnum LOGIN_ERROR = ErrorEnum.LOGIN_ERROR;
    // 重复登录
    public static final ErrorEnum LOGIN_MULTI_ERROR = ErrorEnum.LOGIN_MULTI_ERROR;
    // token错误
    public static final ErrorEnum TOKEN_ERROR = ErrorEnum.TOKEN_ERROR;
    // 记录不存在
    public static final ErrorEnum NO_RECORD = ErrorEnum.RECORD_NOT_FOUND;
    // 批量插入失败
    public static final ErrorEnum INSERT_BATCH_ERROR = ErrorEnum.INSERT_BATCH_ERROR;
    // 批量插入参数错误
    public static final ErrorEnum INSERT_EMPTY_INPUT_ERROR = ErrorEnum.INSERT_EMPTY_INPUT_ERROR;
    // 文件不存在
    public static final ErrorEnum FILE_NOT_FOUND = ErrorEnum.FILE_NOT_FOUND;

    // 位置错误
    public static final ErrorEnum UNKNOWN_ERROR = ErrorEnum.UNKNOWN_ERROR;
}
