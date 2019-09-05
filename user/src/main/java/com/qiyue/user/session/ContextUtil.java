package com.qiyue.user.session;

import com.qiyue.user.dao.entity.UserEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ContextUtil {

    private static HttpServletRequest request =
            ((ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes())
            .getRequest();

    public static UserEntity getUser(){
        return (UserEntity)request.getSession(false).getAttribute("user");
    }
}
