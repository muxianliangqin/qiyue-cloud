package com.qiyue.gateway.filter;

import com.qiyue.base.constant.Constant;
import com.qiyue.base.redis.RedisHandler;
import com.qiyue.base.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@RefreshScope
@Component
public class RequestFilter implements GlobalFilter, Ordered {

    @Value("${login.auth.check.excludes}")
    private String authExcludes;

    @Value("${login.auth.token.expires}")
    private int tokenExpires;

    @Autowired
    private RedisHandler redisHandler;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String uri = request.getURI().getPath();
        String[] uris = authExcludes.split(",");
        if (Arrays.asList(uris).contains(uri)) {
            return chain.filter(exchange);
        }
        HttpHeaders httpHeaders = request.getHeaders();
        List<String> tokens = null;
        String token = null;
        User user = null;
        /*
        鉴权条件，如果发生以下情况，鉴权不通过
        1、header中没有token属性
        2、token属性值为空
        3、token属性值多条
        4、token属性值不在redis中
         */
        if ((tokens = httpHeaders.get(Constant.TOKEN_NAMESPACE)) == null
                || tokens.isEmpty()
                || tokens.size() > 1
                || (user = (User) redisHandler.getHashTemplate().get(Constant.TOKEN_NAMESPACE, token = tokens.get(0))) == null) {
            response.setStatusCode(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
            return response.setComplete();
        }
        redisHandler.getHashTemplate().set(Constant.TOKEN_NAMESPACE, tokens.get(0), user, tokenExpires);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
