package com.qiyue.base.utils;

import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.DatabaseException;

import java.lang.reflect.Field;

public class ReflectUtil {

    public static void setFieldValue(Object object, String fileName, Object fieldValue) {
        Class<?> clazz = object.getClass();
        try {
            Field field = clazz.getDeclaredField(fileName);
            field.setAccessible(true);
            field.set(object, fieldValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new DatabaseException(ErrorEnum.ILLEGAL_ACCESS, fileName, fieldValue);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new DatabaseException(ErrorEnum.NO_SUCH_FIELD, fileName);
        }
    }
}
