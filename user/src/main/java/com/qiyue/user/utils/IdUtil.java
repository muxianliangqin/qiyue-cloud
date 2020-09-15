package com.qiyue.user.utils;

import com.qiyue.base.id.UniqueId;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IdUtil implements InitializingBean {

    @Value(value = "${id.unique.user.workerId}")
    private long workerId;

    @Value(value = "${id.unique.user.dataCenterId}")
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
