package com.qiyue.wechat.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "wechat_record")
public class WeChatRecordEntity {
    @Id
    @Column(name = "record_id")
    private int id;

    @Column(name = "group_nick_name")
    private String groupNickName;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "actual_nick_name")
    private String actualNickName;

    @Column(name = "text_or_filename")
    private String textOrFilename;

    @Column(name = "record_type")
    private String recordType;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "record_time")
    private String recordTime;

}
