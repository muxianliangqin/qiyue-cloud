package com.qiyue.user.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "menu",uniqueConstraints = {
        @UniqueConstraint(columnNames = "code")
})
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "menu_desc")
    private String desc;

    @Column(name = "super_code")
    private String superCode;

    @Column(name = "menu_state")
    private String state;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time")
    private String updateTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "xpath")
    private String xpath;

}
