package com.qiyue.crawler.node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -257501844091438213L;

    private Element element;

    private List<Node> children = new ArrayList<Node>();
    private Node parent;

    public int height = 1;
    public boolean hasChild = false;
    public List<String> breadcrumbs = new ArrayList<>();

    public Node() {
        Root root = new Root("root", "root");
        this.element = root;
    }

    public Node(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public List<String> getBreadcrumbs() {
        return breadcrumbs;
    }

    public void setBreadcrumbs(List<String> path) {
        this.breadcrumbs = path;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (String str:breadcrumbs) {
            sb.append("'").append(str).append("'");
            sb.append(",");
        }
        if (breadcrumbs.size()!=0) {
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append("]");
        return "{" +
                "'element':" + element +
                ", 'children':" + children +
                ", 'breadcrumbs':" + sb.toString() +
//                ", 'parent':" + parent +
                ", 'height':" + height +
                ", 'hasChild':" + hasChild +
                '}';
    }
}
