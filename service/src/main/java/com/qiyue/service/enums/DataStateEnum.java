package com.qiyue.service.enums;

public enum DataStateEnum {
    // 一般初始状态为数据可用、未操作
    ORIGINAL(0, "初始状态"),
    SECOND(1, "第二状态"),
    ;

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
