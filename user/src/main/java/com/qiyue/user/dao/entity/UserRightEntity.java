package com.qiyue.user.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_right")
public class UserRightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "right_code")
    private String roleCode;

    @Column(name = "ur_state")
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
