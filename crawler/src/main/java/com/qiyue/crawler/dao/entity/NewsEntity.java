package com.qiyue.crawler.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "news", uniqueConstraints={
        @UniqueConstraint(columnNames = "url")
})
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "category_id")
    private int categoryId;

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
