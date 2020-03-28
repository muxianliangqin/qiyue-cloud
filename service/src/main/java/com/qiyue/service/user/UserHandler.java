package com.qiyue.service.user;

import com.qiyue.base.constant.Constant;
import com.qiyue.base.redis.RedisHandler;
import com.qiyue.base.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserHandler {

    @Autowired
    private RedisHandler redisHandler;

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public User getUser() {
        String token = getRequest().getHeader(Constant.TOKEN_NAMESPACE);
        User user = (User)redisHandler.getHashTemplate().get(Constant.TOKEN_NAMESPACE, token);
        return user;
    }
}
