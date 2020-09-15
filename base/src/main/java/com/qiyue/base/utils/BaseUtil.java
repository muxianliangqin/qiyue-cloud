package com.qiyue.base.utils;

import com.qiyue.base.constant.Constant;
import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.BusinessException;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseUtil {
    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        boolean flag = false;
        if (str == null || "".equals(str.trim()) || "null".equals(str)) {
            flag = true;
        }
        return flag;
    }

    public static boolean isInt(String str) {
        return str.matches(Constant.IS_INT);
    }

    public static String formatAmount(String str) throws Exception {
        if (isEmpty(str)) {
            str = "0.00";
        }
        if (!str.matches(Constant.AMOUNT_FORMAT)) {
            throw new Exception("金额格式不正确");
        }
        BigDecimal b_amount = new BigDecimal(str);
        return b_amount.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    public static String YuanToFen(String yuan) throws Exception {
        if (isEmpty(yuan)) {
            yuan = "0.00";
        }
        if (!yuan.matches(Constant.AMOUNT_FORMAT)) {
            throw new Exception("金额格式不正确");
        }
        BigDecimal b_yuan = new BigDecimal(yuan);
        return b_yuan.multiply(new BigDecimal(100)).toPlainString();
    }


    public static String getRandomString(int length, int type) {
        StringBuffer sb = new StringBuffer();
        String[] charArr = null;
        if (type == Constant.TYPE_NUMBER) {
            charArr = new String[Constant.NUMBER_ARR.length];
            System.arraycopy(Constant.NUMBER_ARR, 0, charArr, 0, Constant.NUMBER_ARR.length);
        } else if (type == Constant.TYPE_LETTER) {
            charArr = new String[Constant.LETTER_ARR.length];
            System.arraycopy(Constant.LETTER_ARR, 0, charArr, 0, Constant.LETTER_ARR.length);
        } else if (type == Constant.TYPE_MIX) {
            charArr = new String[Constant.NUMBER_ARR.length + Constant.LETTER_ARR.length];
            System.arraycopy(Constant.NUMBER_ARR, 0, charArr, 0, Constant.NUMBER_ARR.length);
            System.arraycopy(Constant.LETTER_ARR, 0, charArr, Constant.NUMBER_ARR.length, Constant.LETTER_ARR.length);
        } else {
            throw new BusinessException(ErrorEnum.RANDOM_STRING_NO_SUCH_TYPE, type);
        }
        SecureRandom random = new SecureRandom();
        byte[] seeds = SecureRandom.getSeed(length);
        for (int i = 0; i < length; i++) {
            random.setSeed(seeds); //设置种子
            int randomIndex = random.nextInt(charArr.length); //随机生成0-charArr.length的数字
            sb.append(charArr[randomIndex]);
            random.nextBytes(seeds); //随机获取新的byte数组用以作为下次的种子，不断循环
        }
        return sb.toString();
    }

    /*
    首先攻击者准备256个字符串，它们的哈希值的第一字节包含了所有可能的情况。
    他将每个字符串发送给在线系统尝试登陆，并记录系统响应所消耗的时间。
    耗时最长的字符串就是第一字节相匹配的。攻击者知道第一字节后，并可以用同样的方式继续猜测第二字节、
    第三字节等等。一旦攻击者获得足够长的哈希值片段，他就可以在自己的机器上来破解，不受在线系统的限制。
    在网络上进行这种攻击似乎不可能。然而，有人已经实现了，并已证明是实用的。
    这就是为什么本文提到的代码，它利用固定时间去比较字符串，而不管有多大的字符串。
     */
    public static boolean slowEquals(String aStr, String bStr) {
        byte[] a = aStr.getBytes(StandardCharsets.UTF_8);
        byte[] b = bStr.getBytes(StandardCharsets.UTF_8);
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    public static String strToUnicode(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            sb.append(Constant.SYMBOL_UNICODE_PREFIX);
            String u = Integer.toHexString(c);
            sb.append(u);
        }
        return sb.toString();
    }

    public static String unicodeToStr(String unicode) {
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(Constant.REGEX_UNICODE);
        Matcher m = p.matcher(unicode);
        while (m.find()) {
            String u = m.group();
            char letter = (char) Integer.parseInt(u.substring(2), 16);
            sb.append(letter);
        }
        return sb.toString();
    }

    public static String mapToString(Map map) {
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                mapToString((Map) value);
            } else {
                System.out.println(key.toString() + ":" + value.toString());
            }
        }
        return null;
    }

    public static String encode(String str, String encode) {
        String output = "";
        try {
            byte[] bt = str.getBytes();
            output = new String(bt, encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static void main(String args[]) throws Exception {

    }
}
