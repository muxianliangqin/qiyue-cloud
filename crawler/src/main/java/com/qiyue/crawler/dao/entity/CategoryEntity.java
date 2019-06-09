package com.qiyue.crawler.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "category", uniqueConstraints={
        @UniqueConstraint(columnNames = {"url","xpath"})
})
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "xpath")
    private String xpath;

    @Column(name = "web_id")
    private int webId;

    @Column(name = "charset")
    private String charset;

    @Column(name = "new_num")
    private int newNum;

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
