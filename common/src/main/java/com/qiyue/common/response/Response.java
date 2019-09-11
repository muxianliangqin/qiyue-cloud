package com.qiyue.common.response;

import com.qiyue.common.enumerate.Error;

public class Response {
    private String errorCode;

    private String errorMsg;

    private Object content;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public static Response success() {
        Response response = new Response();
        response.errorCode = Error.SUCCESS.getCode();
        response.errorMsg = Error.SUCCESS.getMsg();
        response.content = "ok";
        return response;
    }

    public static Response success(Object content) {
        Response response = new Response();
        response.errorCode = Error.SUCCESS.getCode();
        response.errorMsg = Error.SUCCESS.getMsg();
        response.content = content;
        return response;
    }

    public static Response fail(String errorCode, String errorMsg) {
        Response response = new Response();
        response.errorCode = errorCode;
        response.errorMsg = errorMsg;
        return response;
    }

    public static Response fail(String enumName) {
        Response response = new Response();
        Error error = null;
        try {
            error = Error.valueOf(enumName);
        } catch (IllegalArgumentException e) {
            error = Error.valueOf("ENUM_ERROR");
        }
        response.errorCode = error.getCode();
        response.errorMsg = error.getMsg();
        return response;
    }

    public static void main(String args[]) {
        System.out.println(Response.fail("eee"));
    }
}
