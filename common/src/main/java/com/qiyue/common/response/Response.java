package com.qiyue.common.response;

import com.qiyue.common.constant.ErrorConstant;
import com.qiyue.common.enumerate.Error;
import lombok.Data;

@Data
public class Response {
    private String errorCode;

    private String errorMsg;

    private Object content;

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
            e.printStackTrace();
            error = Error.valueOf(ErrorConstant.ENUM_ERROR);
        }
        response.errorCode = error.getCode();
        response.errorMsg = error.getMsg();
        return response;
    }

    public static void main(String args[]) {
        System.out.println(Response.fail("eee"));
    }
}
