package com.qiyue.user.node;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Menu extends Element implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2796297496133610353L;

    private String name;

    private String desc;

    private String url;

    private List<Integer> equalUsers;

    public Menu(){
        super();
    }

    public Menu(String code, String superCode) {
        super(code,superCode);
    }

    public Menu(String code, String name, String superCode) {
        super(code,superCode);
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return "{" +
//                "'code':'" + super.getCode() + '\'' +
//                ", 'superCode':'" + super.getSuperCode() + '\'' +
//                ", 'name':'" + name + '\'' +
//                ", 'desc':'" + desc + '\'' +
//                ", 'url':'" + url + '\'' +
//                ", 'rightEqual':'" + equalUsers + '\'' +
//                '}';
//    }
}
