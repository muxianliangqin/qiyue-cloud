package com.qiyue.user.dao.entity;

import lombok.Data;

import javax.persistence.*;
import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "mobile")
})
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 3237705433860906626L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "alias")
    private String alias;

    @Column(name = "gender")
    private String gender;

    @Column(name = "openid")
    private String openid;

    @Column(name = "user_state")
    private String state;

    @Column(name = "create_time")
    private Date createTime = new Date();

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time")
    private Date updateTime = new Date();

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "salt")
    private String salt;

    @Column(name = "token")
    private String token;

}
