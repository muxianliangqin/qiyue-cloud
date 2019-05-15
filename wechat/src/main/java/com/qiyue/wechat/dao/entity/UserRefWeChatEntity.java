package com.qiyue.wechat.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_ref_group")
public class UserRefWeChatEntity {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "group_nick_name")
    private String groupNickName;

    @Column(name = "state")
    private String state;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time")
    private String updateTime;

    @Column(name = "update_user")
    private String updateUser;

}
