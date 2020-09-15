package com.qiyue.base.exceptions;

import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.utils.StringUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private String errorCode;

    private String errorMsg;

    private Throwable cause;

    public BusinessException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(ErrorEnum errorEnum) {
        this.errorCode = errorEnum.getCode();
        this.errorMsg = errorEnum.getMsg();
    }

    public BusinessException(ErrorEnum errorEnum, Object... messages) {
        this.errorCode = errorEnum.getCode();
        this.errorMsg = StringUtil.format(errorEnum.getMsg(), messages);
    }

    public BusinessException(Throwable cause, ErrorEnum errorEnum, Object... messages) {
        this.errorCode = errorEnum.getCode();
        this.errorMsg = StringUtil.format(errorEnum.getMsg(), messages);
        this.cause = cause;
    }
}
