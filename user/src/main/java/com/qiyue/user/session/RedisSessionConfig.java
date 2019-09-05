package com.qiyue.user.session;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession(redisNamespace="user")
public class RedisSessionConfig {
}
