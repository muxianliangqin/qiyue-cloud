package com.qiyue.crawler.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "category_ref_news", uniqueConstraints={
        @UniqueConstraint(columnNames = "news_xpath")
})
public class CategoryRefNewsEntity {

    @Id
    @Column(name = "crn_id")
    private int id;

    @Column(name = "news_xpath")
    private String newsXpath;

    @Column(name = "category_url")
    private String categoryUrl;

    @Column(name = "crn_state")
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
