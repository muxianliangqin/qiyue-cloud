package com.qiyue.crawler.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "news_info", uniqueConstraints={
        @UniqueConstraint(columnNames = "url")
})
public class NewsEntity {
    @Id
    @Column(name = "news_id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;

    @Column(name = "news_desc")
    private String desc;

    @Column(name = "category_url")
    private String categoryUrl;

    @Column(name = "news_state")
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
