package com.qiyue.service.kafka;

import lombok.Data;

@Data
public class Message<T> {
    /*系统ID*/
    private String systemId;
    /*版本号-时间戳*/
    private long version = System.currentTimeMillis();
    /*标签*/
    private String label;
    /*数据*/
    private T data;
}
