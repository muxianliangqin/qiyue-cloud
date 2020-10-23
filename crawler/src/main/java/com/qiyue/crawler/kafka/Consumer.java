package com.qiyue.crawler.kafka;

import com.qiyue.service.kafka.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer<T> {

//    @KafkaListener(topics = {BaseConstant.KAFKA_CONSUMER_TOPIC_CRAWLER})
    public void receive(ConsumerRecord<String, Message<T>> consumerRecord) {
        Message<T> message = consumerRecord.value();
    }

}
