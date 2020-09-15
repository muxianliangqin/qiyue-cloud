package com.qiyue.user.enums;

/**
 * 用户性别
 */
public enum UserGenderEnum {
    MALE(0, "未知"),
    FEMALE(1, "男"),
    UNKNOWN(2, "女"),
    ;

    private Integer type;

    private String msg;

    UserGenderEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
