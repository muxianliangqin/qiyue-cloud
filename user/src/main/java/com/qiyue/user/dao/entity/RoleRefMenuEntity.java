package com.qiyue.user.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role_ref_menu")
public class RoleRefMenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rrm_id")
    private int id;

    @Column(name = "role_code")
    private String roleCode;

   @Column(name = "menu_code")
    private String menuCode;

    @Column(name = "rrm_state")
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
