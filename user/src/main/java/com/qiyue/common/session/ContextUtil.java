package com.qiyue.common.session;

import com.qiyue.common.constant.Constant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

public class ContextUtil {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
    }

    public static User getUser() {
        return (User) WebUtils.getSessionAttribute(getRequest(), Constant.SESSION_USER);
    }

    public static String getSessionId() {
        return WebUtils.getSessionId(getRequest());
    }

}
