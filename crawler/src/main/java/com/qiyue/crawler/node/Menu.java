package com.qiyue.crawler.node;

import lombok.Data;

import java.io.Serializable;

@Data
public class Menu extends Root implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2796297496133610353L;

    private String name;

    private String desc;

    private String url;

    private String xpath;

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

    @Override
    public String toString() {
        return "{" +
                "'code':'" + super.getCode() + '\'' +
                ", 'supCode':'" + super.getSupCode() + '\'' +
                ", 'name':'" + name + '\'' +
                ", 'desc':'" + desc + '\'' +
                ", 'url':'" + url + '\'' +
                ", 'xpath':'" + xpath + '\'' +
                '}';
    }
}
