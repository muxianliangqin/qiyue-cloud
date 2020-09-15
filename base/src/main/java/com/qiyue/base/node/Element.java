package com.qiyue.base.node;

import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.BusinessException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class Element<E> {

    private String nodeId;

    private String superNodeId;

    private E data;

    public Element() {

    }

    public Element(String nodeId, String superNodeId) {
        this.nodeId = nodeId;
        this.superNodeId = superNodeId;
    }

    public static <E> Element<E> convert(E source, String nodeId, String superNodeId) {
        if (null == source) {
            throw new BusinessException(ErrorEnum.ELEMENT_NOT_NULL_DATA);
        }
        if (null == nodeId) {
            throw new BusinessException(ErrorEnum.ELEMENT_NOT_NULL_NODE_ID);
        }
        Element<E> element = new Element<>();
        element.setNodeId(nodeId);
        element.setSuperNodeId(superNodeId);
        element.setData(source);
        return element;
    }

    public static <E> Element<E> convert(E source, String nodeId) {
        return convert(source, nodeId, null);
    }

    public static <E> Element<E> convert(E source, Function<E, String> getNodeId, String superNodeId) {
        if (null == getNodeId) {
            throw new BusinessException(ErrorEnum.ELEMENT_NOT_NULL_NODE_ID);
        }
        return convert(source, getNodeId.apply(source), superNodeId);
    }

    public static <E> Element<E> convertNotSuperString(E source, Function<E, String> getNodeId) {
        return convert(source, getNodeId.apply(source));
    }

    public static <E> Element<E> convert(E source, Function<E, Long> getNodeId, Long superNodeId) {
        if (null == getNodeId) {
            throw new BusinessException(ErrorEnum.ELEMENT_NOT_NULL_NODE_ID);
        }
        String nodeIdString = returnLongToString(source, getNodeId);
        String superNodeIdString = null;
        if (null != superNodeId) {
            superNodeIdString = String.valueOf(superNodeId);
        }
        return convert(source, nodeIdString, superNodeIdString);
    }

    public static <E> Element<E> convertNotSuperLong(E source, Function<E, Long> getNodeId) {
        return convert(source, getNodeId, null);
    }

    /**
     * 将其他对象转换为Element对象
     * nodeId 是String类型
     *
     * @param source         待转换对象
     * @param getNodeId      获取nodeId的方法
     * @param getSuperNodeId 获取superNodeId的方法，非必选
     * @param <E>            待转换对象泛型
     * @return Element对象
     */
    public static <E> Element<E> convertString(E source, Function<E, String> getNodeId, Function<E, String> getSuperNodeId) {
        if (null == getSuperNodeId) {
            return convertNotSuperString(source, getNodeId);
        } else {
            return convert(source, getNodeId, getSuperNodeId.apply(source));
        }
    }

    public static <E> Element<E> convertLong(E source, Function<E, Long> getNodeId, Function<E, Long> getSuperNodeId) {
        String nodeIdString = returnLongToString(source, getNodeId);
        String superNodeIdString = returnLongToString(source, getSuperNodeId);
        return convert(source, nodeIdString, superNodeIdString);
    }

    public static <E> List<Element<E>> convertString(List<E> list,
                                                     Function<E, String> getNodeId,
                                                     Function<E, String> getSuperNodeId) {
        List<Element<E>> elementList = new ArrayList<>();
        if (null == list) {
            return elementList;
        }
        return list.stream().map(k -> convertString(k, getNodeId, getSuperNodeId)).collect(Collectors.toList());
    }

    public static <E> List<Element<E>> convertPeerString(List<E> list,
                                                         Function<E, String> getNodeId,
                                                         String superNodeId) {
        List<Element<E>> elementList = new ArrayList<>();
        if (null == list || null == getNodeId) {
            return elementList;
        }
        return list.stream().map(k -> convert(k, getNodeId, superNodeId)).collect(Collectors.toList());
    }

    public static <E> List<Element<E>> convertPeerLong(List<E> list,
                                                       Function<E, Long> getNodeId,
                                                       Long superNodeId) {
        List<Element<E>> elementList = new ArrayList<>();
        if (null == list || null == getNodeId) {
            return elementList;
        }
        return list.stream().map(k -> convert(k, getNodeId, superNodeId)).collect(Collectors.toList());
    }

    public static <E> List<Element<E>> convertLong(List<E> list,
                                                   Function<E, Long> getNodeId,
                                                   Function<E, Long> getSuperNodeId) {
        List<Element<E>> elementList = new ArrayList<>();
        if (null == list || null == getNodeId || null == getSuperNodeId) {
            return elementList;
        }
        return list.stream().map(k -> convertLong(k, getNodeId, getSuperNodeId)).collect(Collectors.toList());
    }


    private static <E> String returnLongToString(E source, Function<E, Long> getLong) {
        if (null == source) {
            return null;
        }
        if (null == getLong) {
            return null;
        }
        Long longValue = getLong.apply(source);
        if (null == longValue) {
            return null;
        }
        return String.valueOf(longValue);
    }

    /**
     * 将其他对象转换为Element对象
     *
     * @param source    待转换对象
     * @param getNodeId 获取nodeId的方法
     * @param <E>       待转换对象泛型
     * @return Element对象
     */
    public static <E> Element<E> stringConvert(E source, Function<E, String> getNodeId) {
        return convertNotSuperString(source, getNodeId);
    }

    public static <E> Element<E> longConvert(E source, Function<E, Long> getNodeId) {
        return convertNotSuperLong(source, getNodeId);
    }

    public static <E> List<Element<E>> stringConvert(List<E> list, Function<E, String> getNodeId) {
        return convertString(list, getNodeId, null);
    }

    public static <E> List<Element<E>> longConvert(List<E> list, Function<E, Long> getNodeId) {
        return convertLong(list, getNodeId, null);
    }

}
