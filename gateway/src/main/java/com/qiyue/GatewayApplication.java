package com.qiyue;

import com.qiyue.common.constant.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@RefreshScope
@SpringBootApplication
@EnableZuulProxy
@EnableRedisHttpSession(redisNamespace = Constant.SESSION_REDIS_NAME_SPACE, redisFlushMode = RedisFlushMode.IMMEDIATE)
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
