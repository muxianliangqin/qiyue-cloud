package com.qiyue.base.exceptions;

import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.utils.StringUtil;
import lombok.Data;

@Data
public class DatabaseException extends RuntimeException {

    private String errorCode;

    private String errorMsg;

    public DatabaseException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public DatabaseException(ErrorEnum errorEnum) {
        this.errorCode = errorEnum.getCode();
        this.errorMsg = errorEnum.getMsg();
    }

    public DatabaseException(ErrorEnum errorEnum, Object... messages) {
        this.errorCode = errorEnum.getCode();
        this.errorMsg = StringUtil.format(errorEnum.getMsg(), messages);
    }
}
