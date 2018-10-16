package com.qiyue.crawler.node;

public class Root implements Element {
    private String code;

    private String supCode;

    @Override
    public String getCode() {
        return code;
    }

    public Root(){

    }

    public Root(String code, String supCode) {
        this.code = code;
        this.supCode = supCode;
    }

    @Override
    public String getSupCode() {
        return supCode;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSupCode(String supCode) {
        this.supCode = supCode;
    }

    @Override
    public String toString() {
        return "{" +
                "'code':'" + code + '\'' +
                ", 'supCode':'" + supCode + '\'' +
                '}';
    }
}
