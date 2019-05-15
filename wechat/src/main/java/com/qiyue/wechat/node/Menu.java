package com.qiyue.wechat.node;

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

    public Menu(String id, String supId) {
        super(id,supId);
    }

    public Menu(String id, String name, String supId) {
        super(id,supId);
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "'id':'" + super.getId() + '\'' +
                ", 'supId':'" + super.getSupId() + '\'' +
                ", 'name':'" + name + '\'' +
                ", 'desc':'" + desc + '\'' +
                ", 'url':'" + url + '\'' +
                ", 'xpath':'" + xpath + '\'' +
                '}';
    }
}