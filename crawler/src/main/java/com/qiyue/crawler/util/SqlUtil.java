package com.qiyue.crawler.util;


import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;

import java.lang.reflect.Field;

public class SqlUtil {

	public static Object updateNoNull(Object src,Object target) {
		Assert.notNull(src, "src不能为空");
		Assert.notNull(target, "target不能为空");
		String srcType = src.getClass().getTypeName();
		String tarType = target.getClass().getTypeName();
		Assert.isTrue(srcType.equals(tarType), "src和target类型必须相同");
		Field [] srcFields = src.getClass().getDeclaredFields();
		for (Field field:srcFields) {
			try {
				field.setAccessible(true);
				Object value = field.get(src);
				if (value != null) {
					continue;
				}
				String fieldName = field.getName();
				Field tField = target.getClass().getDeclaredField(fieldName);
				tField.setAccessible(true);
				BeanWrapper tBean = new BeanWrapperImpl(target);
				Object tValue = tBean.getPropertyValue(tField.getName());

				field.set(src, tValue);

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

		}
		return src;
	}
}
