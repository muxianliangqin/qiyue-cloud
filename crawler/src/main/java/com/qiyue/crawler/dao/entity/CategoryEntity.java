package com.qiyue.crawler.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "category_info", uniqueConstraints={
        @UniqueConstraint(columnNames = {"url","xpath"})
})
public class CategoryEntity {
    @Id
    @Column(name = "category_id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;

    @Column(name = "category_desc")
    private String desc;

    @Column(name = "xpath")
    private String xpath;

    @Column(name = "web_url")
    private String webUrl;

    @Column(name = "category_state")
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
