package com.qiyue.user.enums;

import com.qiyue.user.constant.MenuPermissionConstant;

/**
 * 前端页面，该角色对组件的权限，0-show，展示，1-disabled，展示但禁用
 */
public enum MenuPermissionEnum {
    SHOW(1, MenuPermissionConstant.SHOW, "展示"),
    DISABLED(2, MenuPermissionConstant.DISABLED, "禁用"),
    HIDDEN(3, MenuPermissionConstant.HIDDEN, "隐藏"),
    ;

    private Integer type;
    private String code;
    private String name;

    MenuPermissionEnum(Integer type, String code, String name) {
        this.type = type;
        this.code = code;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
