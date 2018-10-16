package com.qiyue.crawler.node;

public class NodeTree {
    private static Node node = new Node();

    public static Node getInstance(){
        node = new Node();
        return node;
    }

    public static boolean insert(Node node,Element ele) {
        boolean flag = false;
        if (node.getElement() == null) {
            node.setElement(ele);
            flag = true;
        } else {
            String code = node.getElement().getCode();
            String supCode = ele.getSupCode();
            if (supCode == null || "".equals(supCode)) {
                Node subNode = new Node(ele);
                subNode.setParent(NodeTree.node);
                NodeTree.node.getChildren().add(subNode);
                NodeTree.node.hasChild = true;
                subNode.breadcrumbs.add(ele.getCode());
                flag = true;
            } else if (code.equals(supCode)) {
                Node subNode = new Node(ele);
                subNode.setParent(node);
                node.getChildren().add(subNode);
                node.hasChild = true;
                subNode.breadcrumbs.addAll(node.breadcrumbs);
                subNode.breadcrumbs.add(ele.getCode());
                flag = true;
            } else {
                for (Node child:node.getChildren()) {
                    if (insert(child,ele)){
                        flag = true;
                        break;
                    }
                }
            }
        }
        if (node.hasChild) {
            node.height = node.getChildren().get(0).getHeight() +1;
        }
        return flag;
    }
}
