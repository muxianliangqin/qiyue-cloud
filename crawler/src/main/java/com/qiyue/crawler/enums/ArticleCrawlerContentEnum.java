package com.qiyue.crawler.enums;

public enum ArticleCrawlerContentEnum {
    NO(0, "未成功"),
    YES(1, "已成功"),
    ;

    private Integer status;
    private String msg;

    ArticleCrawlerContentEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
