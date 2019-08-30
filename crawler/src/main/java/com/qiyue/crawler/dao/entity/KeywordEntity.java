package com.qiyue.crawler.dao.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "keyword")
public class KeywordEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "reg_exp")
    private String regexp;

    @Column(name = "reg_codes")
    private String codes;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time")
    private String updateTime;

    @Column(name = "update_user")
    private String updateUser;

}
