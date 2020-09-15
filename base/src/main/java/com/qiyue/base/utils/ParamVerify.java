package com.qiyue.base.utils;

import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.ParamException;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class ParamVerify {

    /**
     * 参数不能为空
     *
     * @param obj       参数
     * @param paramDesc 提示信息 参数字段说明
     * @param paramName 提示信息 参数字段名称
     */
    public static void isNotNull(Object obj, String paramDesc, String paramName) {
        if (null == obj) {
            throw new ParamException(ErrorEnum.PARAM_CANNOT_NULL, paramDesc, paramName);
        }
        if (obj instanceof List && ((List) obj).size() == 0) {
            throw new ParamException(ErrorEnum.PARAM_LIST_CANNOT_EMPTY, paramDesc, paramName);
        }
    }

    /**
     * 此处空格也算是空字符串
     *
     * @param obj       参数
     * @param paramDesc 提示信息 参数字段说明
     * @param paramName 提示信息 参数字段名称
     */
    public static void isNotNull(String obj, String paramDesc, String paramName) {
        if (StringUtils.isEmpty(obj)) {
            throw new ParamException(ErrorEnum.PARAM_CANNOT_NULL, paramDesc, paramName);
        }
    }

    /**
     * 校验参数的有效性，是不是在预设的集合中
     *
     * @param value     参数
     * @param paramName 提示信息 参数字段名称
     * @param iterable  可遍历对象
     * @param <E>       泛型
     */
    public static <E> void validity(E value, Iterable<E> iterable, String paramName) {
        boolean isValid = false;
        Iterator<E> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            if (value.equals(iterator.next())) {
                isValid = true;
            }
        }
        if (!isValid) {
            throw new ParamException(ErrorEnum.PARAM_INVALID, paramName, value, iterable);
        }
    }

    /**
     * 根据枚举的设置，校验参数的有效性，是不是在预设的集合中
     * @param enums     枚举数组
     * @param getSource 枚举获取源值的方法
     * @param paramName 参数名称
     * @param <S>       源值泛型
     * @param <E>       枚举泛型
     */
    public static <S, E> void validity(S source, E[] enums, Function<E, S> getSource, String paramName) {
        if (!EnumUtil.include(source, enums, getSource)) {
            throw new ParamException(ErrorEnum.PARAM_INVALID, paramName, source, EnumUtil.toList(enums, getSource));
        }
    }

}
