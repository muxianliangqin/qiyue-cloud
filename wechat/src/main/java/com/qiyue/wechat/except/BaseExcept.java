package com.qiyue.wechat.except;

public class BaseExcept extends RuntimeException {

    private String errorCode;

    private String errorMsg;

    public BaseExcept(String errorCode,String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
