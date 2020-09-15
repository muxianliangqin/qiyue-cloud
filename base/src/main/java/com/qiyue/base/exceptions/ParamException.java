package com.qiyue.base.exceptions;

import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.utils.StringUtil;
import lombok.Data;

@Data
public class ParamException extends RuntimeException {

    private String errorCode;

    private String errorMsg;

    public ParamException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ParamException(ErrorEnum errorEnum) {
        this.errorCode = errorEnum.getCode();
        this.errorMsg = errorEnum.getMsg();
    }

    public ParamException(ErrorEnum errorEnum, Object... messages) {
        this.errorCode = errorEnum.getCode();
        this.errorMsg = StringUtil.format(errorEnum.getMsg(), messages);
    }
}
