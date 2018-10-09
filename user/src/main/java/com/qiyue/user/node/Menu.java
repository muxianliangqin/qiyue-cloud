package com.qiyue.user.node;

import java.io.Serializable;

public class Menu extends Root implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2796297496133610353L;

    private String name;

    private String desc;

    private String url;

    public Menu() {
        super();
    }

    public Menu(String code, String supCode) {
        super(code,supCode);
    }

    public Menu(String code, String name, String supCode) {
        super(code,supCode);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "{" +
                "'code':'" + super.getCode() + '\'' +
                ", 'supCode':'" + super.getSupCode() + '\'' +
                ", 'name':'" + name + '\'' +
                ", 'desc':'" + desc + '\'' +
                ", 'url':'" + url + '\'' +
                '}';
    }
}
