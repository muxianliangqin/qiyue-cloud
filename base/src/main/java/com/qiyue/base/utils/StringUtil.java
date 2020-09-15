package com.qiyue.base.utils;

import com.qiyue.base.constant.Constant;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 格式化字符串
     *
     * @param source 待格式化字符串，以{}为占位符，
     * @param replaceValues 替换值数组
     * @return 格式化后的字符串
     */
    public static String format(String source, Object... replaceValues) {
        Pattern pattern = Pattern.compile("\\{}");
        Matcher matcher = pattern.matcher(source);
        StringBuffer sb = new StringBuffer();
        // 从开始替换占位符
        for (Object v : replaceValues) {
            if (matcher.find() && null != v) {
                // 所有替换值都转为String形式
                matcher.appendReplacement(sb, v.toString());
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
