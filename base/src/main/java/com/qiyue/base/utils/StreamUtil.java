package com.qiyue.base.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamUtil {

    /**
     * 选取list对象中的某个指定属性返回
     *
     * @param list      集合
     * @param specified 指定属性
     * @param <E>       对象泛型
     * @param <R>       指定属性泛型
     * @return 指定属性值的集合
     */
    public static <E, R> List<R> pickSpecific(List<E> list, Function<E, R> specified) {
        if (null == list || null == specified) {
            return new ArrayList<>();
        }
        return list.stream().map(specified).collect(Collectors.toList());
    }

    /**
     * 将源对象转换为目标对象
     *
     * @param source    源对象
     * @param converter 转换器
     * @param <S>       源对象泛型
     * @param <T>       目标对象泛型
     * @return 目标对象
     */
    public static <S, T> T map(S source, Function<S, T> converter) {
        if (null == source || null == converter) {
            return null;
        }
        return converter.apply(source);
    }

    /**
     * 集合
     * 将源对象转换为目标对象
     *
     * @param list      源对象集合
     * @param converter 转换器
     * @param <S>       源对象泛型
     * @param <T>       目标对象泛型
     * @return 目标对象集合
     */
    public static <S, T> List<T> map(List<S> list, Function<S, T> converter) {
        if (null == list || null == converter) {
            return new ArrayList<>();
        }
        return list.stream().map(converter).collect(Collectors.toList());
    }

    /**
     * （计算机）读取数据、改变原对象数据
     *
     * @param object 对象
     * @param peek   操作数据的方法
     * @param <E>    对象泛型
     * @return 原对象
     */
    public static <E> E peek(E object, Consumer<E> peek) {
        if (null == object || null == peek) {
            return object;
        }
        peek.accept(object);
        return object;
    }

    /**
     * 处理集合
     * 计算机）读取数据、改变原对象数据
     *
     * @param list 对象集合
     * @param peek 操作数据的方法
     * @param <E>  对象泛型
     * @return 原对象集合
     */
    public static <E> List<E> peek(List<E> list, Consumer<E> peek) {
        if (null == list || null == peek) {
            return list;
        }
        return list.stream().peek(peek).collect(Collectors.toList());
    }
}
