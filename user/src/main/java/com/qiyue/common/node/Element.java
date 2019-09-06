package com.qiyue.common.node;

import lombok.Data;

@Data
public class Element {
    private String code;

    private String superCode;

    public Element() {

    }

    public Element(String code, String superCode) {
        this.code = code;
        this.superCode = superCode;
    }

}
