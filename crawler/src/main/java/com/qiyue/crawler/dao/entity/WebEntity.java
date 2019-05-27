package com.qiyue.crawler.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "web", uniqueConstraints={
        @UniqueConstraint(columnNames = {"url"})
})
public class WebEntity implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

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

    @Column(name = "user_id")
    private int userId;

    @OneToMany
    @JoinColumn(name = "web_url",referencedColumnName = "url")
    private List<CategoryEntity> categories;

}
