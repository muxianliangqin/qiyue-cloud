package com.qiyue.crawler.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "category", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"url", "xpath_title"})
})
public class CategoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "xpath_title")
    private String xpathTitle;

    @Column(name = "xpath_text")
    private String xpathText;

    @Column(name = "web_id")
    private Integer webId;

    @Column(name = "charset")
    private String charset;

    @Column(name = "new_num")
    private Integer newNum;

    @Column(name = "category_state")
    private String state;

    @Column(name = "create_time")
    private Date createTime = new Date();

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time")
    private Date updateTime = new Date();

    @Column(name = "update_user")
    private String updateUser;

}
