package com.qiyue.user.node;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Node implements Serializable {
    private static final long serialVersionUID = -257501844091438213L;

    private Element element;
    private List<Node> children = new ArrayList<Node>();
    public int height = 0;
    public boolean hasChild = false;
    public List<String> breadcrumbs = new ArrayList<>();

    public Node() {
        Element root = new Element("root", "root");
        this.element = root;
    }

    public Node(Element element) {
        this.element = element;
    }

    public boolean insert(Node node, Element element) {
        if (element == null) {
            return false;
        }
        String supCode = element.getSuperCode();
        if (StringUtils.isEmpty(supCode)) {
            Node subNode = new Node(element);
            node.getChildren().add(subNode);
            node.hasChild = true;
            subNode.breadcrumbs.add(element.getCode());
            subNode.height = node.height + 1;
            return true;
        }
        for (Node child:node.getChildren()) {
            String code = child.getElement().getCode();
            if (supCode.equals(code)) {
                Node subNode = new Node(element);
                child.getChildren().add(subNode);
                child.hasChild = true;
                subNode.breadcrumbs.addAll(child.breadcrumbs);
                subNode.breadcrumbs.add(element.getCode());
                subNode.height = child.height + 1;
                return true;
            } else {
                if (insert(child, element)) {
                    return true;
                }
            }
        }
        return false;
    }

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

        Node node = new Node();
        menus.forEach(menu->{
            node.insert(node,menu);
        });
        System.out.println(node);
    }
}
