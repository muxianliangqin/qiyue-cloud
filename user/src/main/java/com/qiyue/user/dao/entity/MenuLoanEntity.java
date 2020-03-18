package com.qiyue.user.dao.entity;

import lombok.Data;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.util.Date;

/*
@FilterDef:与@OneToMany的one表@Filter配合使用，type:java.lang.Integer或integer都行
 */
@Data
@Entity
@Table(name = "menu_loan")
@FilterDef(name = "userIdFilter",
        parameters = {@ParamDef(name = "userId", type = "java.lang.Integer")})
public class MenuLoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "menu_code")
    private String menuCode;

    @Column(name = "loan_user_id")
    private int loanUserId;

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
