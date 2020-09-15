package com.qiyue.user.enums;

import com.qiyue.user.constant.MenuOperateConstant;

/**
 * 执行后台操作的类型，1-read:只读，2-add:新增,3-update:更新，4-delete:删除
 */
public enum MenuOperateEnum {
    SELECT(1, MenuOperateConstant.SELECT, "查询"),
    INSERT(2, MenuOperateConstant.INSERT, "插入"),
    UPDATE(3, MenuOperateConstant.UPDATE, "更新"),
    DELETE(4, MenuOperateConstant.DELETE, "删除"),
    ;

    private Integer type;
    private String code;
    private String name;

    MenuOperateEnum(Integer type, String code, String name) {
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
