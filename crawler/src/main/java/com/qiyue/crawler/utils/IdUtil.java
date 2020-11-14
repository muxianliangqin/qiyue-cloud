package com.qiyue.crawler.utils;

import com.qiyue.base.id.UniqueId;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
public class IdUtil implements InitializingBean {

    @Value(value = "${id.unique.java.crawler.workerId}")
    private long workerId;

    @Value(value = "${id.unique.java.crawler.dataCenterId}")
    private long dataCenterId;

    private static UniqueId uniqueId;

    @Override
    public void afterPropertiesSet() throws Exception {
        uniqueId = new UniqueId(workerId, dataCenterId);
    }

    public static long nextId() {
        return uniqueId.nextId();
    }
}
