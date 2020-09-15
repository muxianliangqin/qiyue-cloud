package com.qiyue.service.utils;

import com.qiyue.base.constant.Constant;
import com.qiyue.base.redis.RedisHandler;
import com.qiyue.base.model.bo.UserInfoBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class ContextUtil implements InitializingBean {

    private static RedisHandler redis;

    @Resource
    private RedisHandler redisHandler;

    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static UserInfoBO getUser() {
        UserInfoBO userInfoBO = new UserInfoBO();
        String token = getRequest().getHeader(Constant.TOKEN_NAMESPACE);
        if (StringUtils.isNotEmpty(token)) {
            Object object = redis.getHashTemplate().get(Constant.TOKEN_NAMESPACE, token);
            if (null != object) {
                userInfoBO = (UserInfoBO) object;
            }
        }
        return userInfoBO;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        redis = redisHandler;
    }
}
