package com.qiyue.crawler.node;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyTest {

    public static void main(String[] args) {
        Menu module1 = new Menu("module1","module1","");
        Menu menu1 = new Menu("menu1","menu1","module1");
        Menu subMenu1 = new Menu("subMenu1","subMenu1","menu1");
        Menu subMenu2 = new Menu("subMenu2","subMenu2","menu1");
        Menu menu2 = new Menu("menu2","menu2","module1");
        Menu subMenu3 = new Menu("subMenu3","subMenu3","menu2");
        Menu subMenu4 = new Menu("subMenu4","subMenu4","menu2");

        Menu module2 = new Menu("module2","module2","");
        Menu menu3 = new Menu("menu3","menu3","module2");
        Menu subMenu5 = new Menu("subMenu5","subMenu5","menu3");
        Menu subMenu6 = new Menu("subMenu6","subMenu6","menu3");
        Menu menu4 = new Menu("menu4","menu4","module2");
        Menu subMenu7 = new Menu("subMenu7","subMenu7","menu4");
        Menu subMenu8 = new Menu("subMenu8","subMenu8","menu4");

        List<Menu> menus = new ArrayList<>();
        menus.add(module1);
        menus.add(module2);
        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);
        menus.add(subMenu1);
        menus.add(subMenu2);
        menus.add(subMenu3);
        menus.add(subMenu4);
        menus.add(subMenu5);
        menus.add(subMenu6);
        menus.add(subMenu7);
        menus.add(subMenu8);

        Node node = NodeTree.getInstance();
        menus.forEach(menu->{
            NodeTree.insert(node,menu);
        });
        System.out.println(node);
        JSONObject json = JSON.parseObject(node.toString());
        System.out.println(json.toJSONString());
    }
}
