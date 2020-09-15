package com.qiyue.user.enums;

import com.qiyue.user.constant.MenuOperateConstant;
import com.qiyue.user.constant.MenuTypeConstant;

/**
 * 类型：
 * 1-模块
 * 2-菜单目录。
 * 3-菜单。
 * 4-页面，非菜单直接打开的页面
 * 5-按钮
 * 6-链接
 */
public enum MenuTypeEnum {
    MODULE(1, MenuTypeConstant.MODULE, "模块"),
    MENU_FOLDER(2, MenuTypeConstant.MENU_FOLDER, "菜单目录"),
    MENU(3, MenuTypeConstant.MENU, "菜单"),
    HTML(4, MenuTypeConstant.HTML, "页面"),
    BUTTON(5, MenuTypeConstant.BUTTON, "按钮"),
    LINK(6, MenuTypeConstant.LINK, "链接"),
    ;

    private Integer type;
    private String code;
    private String name;

    MenuTypeEnum(Integer type, String code, String name) {
        this.type = type;
        this.code = code;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
