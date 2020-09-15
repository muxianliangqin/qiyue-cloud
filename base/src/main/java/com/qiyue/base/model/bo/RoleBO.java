package com.qiyue.base.model.bo;

import lombok.Data;

import java.util.Date;

@Data
public class RoleBO {

    private Long id;

    private Long roleId;

    private String name;

    private String desc;

    private Integer state;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}
