package com.qiyue.common.node;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Node implements Serializable {
    private static final long serialVersionUID = -257501844091438213L;

    private Element element;
    private List<Node> children = new ArrayList<>();
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

    public static Node insertBatch(List<? extends Element> elements){
        Node node = new Node();
        elements.forEach(k -> {
            node.insert(node, k);
        });
        return node;
    }

    public static void main(String[] args) {

    }
}
