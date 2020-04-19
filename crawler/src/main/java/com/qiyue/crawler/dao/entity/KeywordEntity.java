package com.qiyue.crawler.dao.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "keyword")
public class KeywordEntity implements Serializable {
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
    private Date createTime = new Date();

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time")
    private Date updateTime = new Date();

    @Column(name = "update_user")
    private String updateUser;

}
