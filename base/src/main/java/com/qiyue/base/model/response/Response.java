package com.qiyue.base.model.response;

import com.qiyue.base.enums.ErrorEnum;
import lombok.Data;

@Data
public class Response<T> {
    private String code;

    private String message;

    private T content;

    public static Response<String> success() {
        Response<String> response = new Response<>();
        response.code = ErrorEnum.SUCCESS.getCode();
        response.message = ErrorEnum.SUCCESS.getMsg();
        response.content = "ok";
        return response;
    }

    public static <T> Response<T> success(T content) {
        Response<T> response = new Response<>();
        response.code = ErrorEnum.SUCCESS.getCode();
        response.message = ErrorEnum.SUCCESS.getMsg();
        response.content = content;
        return response;
    }

    public static Response fail(String errorCode, String errorMsg) {
        Response response = new Response();
        response.code = errorCode;
        response.message = errorMsg;
        return response;
    }

    public static Response fail(ErrorEnum errorEnum) {
        Response response = new Response();
        response.code = errorEnum.getCode();
        response.message = errorEnum.getMsg();
        return response;
    }

    public static void main(String args[]) {
    }
}
