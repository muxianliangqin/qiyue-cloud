package com.qiyue.base.utils;

import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.BusinessException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class EnumUtil {

    /**
     * 通过枚举转换参数值
     *
     * @param source    源值
     * @param enums     枚举数组
     * @param getSource 枚举获取源值的方法
     * @param getTarget 枚举获取目标值的方法
     * @param <S>       源值泛型
     * @param <E>       枚举泛型
     * @param <T>       目标值泛型
     * @return 目标值
     */
    public static <S, E, T> T convert(S source,
                                      E[] enums,
                                      Function<E, S> getSource,
                                      Function<E, T> getTarget) {
        if (null == getTarget) {
            return null;
        }
        E targetEnum = find(source, enums, getSource);
        if (null == targetEnum) {
            return null;
        }
        return getTarget.apply(targetEnum);
    }

    /**
     * 根据参数值获得枚举对象
     *
     * @param source    源值
     * @param enums     枚举数组
     * @param getSource 枚举获取源值的方法
     * @param <S>       源值泛型
     * @param <E>       枚举泛型
     * @return 枚举对象
     */
    public static <S, E> E find(S source, E[] enums, Function<E, S> getSource) {
        if (null == source || null == enums || null == getSource) {
            return null;
        }
        for (E anEnum : enums) {
            if (source.equals(getSource.apply(anEnum))) {
                return anEnum;
            }
        }
        return null;
    }

    /**
     * 判断某个值是否为枚举中的值
     *
     * @param source    源值
     * @param enums     枚举数组
     * @param getSource 枚举获取源值的方法
     * @param <S>       源值泛型
     * @param <E>       枚举泛型
     * @return 布尔值
     */
    public static <S, E> boolean include(S source, E[] enums, Function<E, S> getSource) {
        return null != find(source, enums, getSource);
    }

    /**
     * 获得枚举某个属性的集合
     *
     * @param enums     枚举数组
     * @param getSource 枚举获取源值的方法
     * @param <S>       源值泛型
     * @param <E>       枚举泛型
     * @return 属性值集合
     */
    public static <S, E> List<S> toList(E[] enums, Function<E, S> getSource) {
        List<S> sList = new ArrayList<>();
        if (null == enums || null == getSource) {
            return sList;
        }
        for (E anEnum : enums) {
            sList.add(getSource.apply(anEnum));
        }
        return sList;
    }

}
