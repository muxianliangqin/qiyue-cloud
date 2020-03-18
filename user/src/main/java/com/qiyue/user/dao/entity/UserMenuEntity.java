package com.qiyue.user.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_menu")
public class UserMenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "menu_code")
    private String menuCode;

    @Column(name = "state")
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
