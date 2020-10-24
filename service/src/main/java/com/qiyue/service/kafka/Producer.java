package com.qiyue.service.kafka;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;

@Slf4j
@Component
public class Producer {

    @Value(value = "${spring.application.name}")
    private String systemId;

    @Value(value = "${kafka.topic.crawler}")
    private String topic;

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    public <T> void send(Message<T> message) {
        if (StringUtils.isEmpty(message.getSystemId())) {
            message.setSystemId(systemId);
        }
        String messageStr = JSONObject.toJSONString(message);
        ListenableFuture<SendResult<String, Object>> sendResult = kafkaTemplate.send(topic, messageStr);
        sendResult.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("消息推送失败，message：{}", JSONObject.toJSONString(message));
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.error("消息推送成功，message：{}", JSONObject.toJSONString(message));
            }
        });
    }
}
