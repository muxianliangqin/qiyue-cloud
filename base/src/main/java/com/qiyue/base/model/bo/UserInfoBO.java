package com.qiyue.base.model.bo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class UserInfoBO implements Serializable {

    private Long id;

    private Long userId;

    private String mobile;

    private String email;

    private String username;

    private String password;

    private String alias;

    private Integer gender;

    private String salt;

    private String token;

    private String openid;

    private Integer state;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private List<RoleBO> roles = new ArrayList<>();

}
