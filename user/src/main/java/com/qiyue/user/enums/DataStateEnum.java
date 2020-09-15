package com.qiyue.user.enums;

/**
 * 数据库数据是否可用
 */
public enum DataStateEnum {
    USABLE(0, "可用"),
    UNUSABLE(1, "不可用");

    private Integer state;

    private String msg;

    DataStateEnum(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
