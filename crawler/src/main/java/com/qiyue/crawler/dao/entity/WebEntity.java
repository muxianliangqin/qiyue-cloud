package com.qiyue.crawler.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "web_info", uniqueConstraints={
        @UniqueConstraint(columnNames = {"url","xpath"})
})
public class WebEntity {

    @Id
    @Column(name = "web_id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;

    @Column(name = "web_desc")
    private String desc;

    @Column(name = "xpath")
    private String xpath;

    @Column(name = "encode")
    private String encode;

    @Column(name = "web_state")
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
