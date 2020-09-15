package com.qiyue.base.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@Data
public class TreeNode implements Serializable {
    private static final long serialVersionUID = -257501844091438213L;

    // 节点元素
    private Element element;
    // 子节点
    private List<TreeNode> children = new ArrayList<>();
    // 父节点
    @JsonIgnore
    private TreeNode parent;
    // 节点深度
    private int depth = 0;
    // 是否有子节点
    private boolean hasChild = false;
    // 到达节点的路径
    private List<String> path = new ArrayList<>();
    /**
     * 判断superNodeId是什么值时，元素作为根节点（这里的根节点是指root下的节点）
     * 默认是 字符串判空
     */
    @JsonIgnore
    private Predicate<String> isRoot = TreeNode.ROOT_STRING_EMPTY;
    // superNodeId判空时作为根节点
    public static final Predicate<String> ROOT_STRING_EMPTY = StringUtils::isEmpty;
    // superNodeId为 0 时作为根节点，此时主要是处理以数字作为id的元素，转换String问题
    public static final Predicate<String> ROOT_NUMBER_ZERO = "0"::equals;

    public TreeNode() {
        this.element = new Element("root", "root");
    }

    public TreeNode(Element element) {
        this.element = element;
    }

    public boolean insert(TreeNode node, Element element) {
        if (element == null) {
            return false;
        }
        String superNodeId = element.getSuperNodeId();
        if (isRoot.test(superNodeId)) {
            TreeNode subNode = new TreeNode(element);
            node.getChildren().add(subNode);
            node.hasChild = true;
            subNode.path.add(element.getNodeId());
            subNode.depth = node.depth + 1;
            subNode.setParent(node);
            return true;
        }
        for (TreeNode child : node.getChildren()) {
            String nodeId = child.getElement().getNodeId();
            if (superNodeId.equals(nodeId)) {
                TreeNode subNode = new TreeNode(element);
                child.getChildren().add(subNode);
                child.hasChild = true;
                subNode.path.addAll(child.path);
                subNode.path.add(element.getNodeId());
                subNode.depth = child.depth + 1;
                subNode.setParent(child);
                return true;
            } else {
                if (insert(child, element)) {
                    return true;
                }
            }
        }
        return false;
    }

    private TreeNode root() {
        TreeNode current, parent;
        current = this;
        while (null != (parent = this.parent)) {
            current = parent;
        }
        return current;
    }

    public <T> TreeNode insertBatch(List<? extends Element<T>> elements) {
        TreeNode root = root();
        elements.forEach(k -> {
            root.insert(root, k);
        });
        return root;
    }

    /**
     * 将集合转换成TreeNode对象
     *
     * @param elements      集合
     * @param rootCondition 判断元素是否为根节点的条件
     * @param <E>           集合泛型
     * @return TreeNode对象
     */
    public static <E> TreeNode convert(List<? extends Element<E>> elements, Predicate<String> rootCondition) {
        TreeNode treeNode = new TreeNode();
        treeNode.setIsRoot(rootCondition);
        treeNode = treeNode.insertBatch(elements);
        return treeNode;
    }

    public static <E> TreeNode convert(List<? extends Element<E>> elements) {
        TreeNode treeNode = new TreeNode();
        treeNode.setIsRoot(TreeNode.ROOT_STRING_EMPTY);
        treeNode = treeNode.insertBatch(elements);
        return treeNode;
    }

    public static <E> TreeNode convertString(List<E> list, Function<E, String> getNodeId, Function<E, String> getSuperNodeId) {
        List<Element<E>> elementList = Element.convertString(list, getNodeId, getSuperNodeId);
        return TreeNode.convert(elementList, TreeNode.ROOT_STRING_EMPTY);
    }

    public static <E> TreeNode convertLong(List<E> list, Function<E, Long> getNodeId, Function<E, Long> getSuperNodeId) {
        List<Element<E>> elementList = Element.convertLong(list, getNodeId, getSuperNodeId);
        return TreeNode.convert(elementList, TreeNode.ROOT_NUMBER_ZERO);
    }

    public static void main(String[] args) {

    }
}
