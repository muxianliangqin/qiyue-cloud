package com.qiyue.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id"),
        @UniqueConstraint(columnNames = "mobile"),
        @UniqueConstraint(columnNames = "username")
})
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 3237705433860906626L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

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
    private Integer gender;

    @Column(name = "salt")
    private String salt;

    @Column(name = "token")
    private String token;

    @Column(name = "openid")
    private String openid;

    @Column(name = "state")
    private Integer state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_user")
    private Long createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_user")
    private Long updateUser;

}
