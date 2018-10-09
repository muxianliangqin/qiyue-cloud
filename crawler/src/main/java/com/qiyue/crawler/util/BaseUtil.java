package com.qiyue.crawler.util;

import com.qiyue.crawler.constant.Constant;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseUtil {
	public final static int TYPE_NUMBER = 0;//数字
	public final static int TYPE_LETTER = 1;//字母
	public final static int TYPE_MIX = 2;//混合

	/**
	 * 判断字符串是否为空
	 */
	public static boolean isEmpty(String str){
		boolean flag = false;
		if (str==null||"".equals(str.trim())||"null".equals(str)){
			flag = true;
		}
		return flag;
	}

	public static boolean isInt(String str){
		return str.matches(Constant.IS_INT);
	}

	public static String formatAmount(String str) throws Exception{
		if (isEmpty(str)){
			str = "0.00";
		}
		if (!str.matches(Constant.AMOUNT_FORMAT)){
			throw new Exception("金额格式不正确");
		}
		BigDecimal b_amount = new BigDecimal(str);
		return b_amount.setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString();
	}
	public static String YuanToFen(String yuan) throws Exception{
		if (isEmpty(yuan)){
			yuan = "0.00";
		}
		if (!yuan.matches(Constant.AMOUNT_FORMAT)){
			throw new Exception("金额格式不正确");
		}
		BigDecimal b_yuan = new BigDecimal(yuan);
		return b_yuan.multiply(new BigDecimal(100)).toPlainString();
	}


	public static String getRandomString(int length,int type) throws Exception{
		StringBuffer sb = new StringBuffer();
		String [] charArr = null;
		if (type == TYPE_NUMBER){
			charArr = new String[Constant.NUMBER_ARR.length ];
			System.arraycopy(Constant.NUMBER_ARR, 0, charArr, 0, Constant.NUMBER_ARR.length);
		} else if (type == TYPE_LETTER){
			charArr = new String[Constant.LETTER_ARR.length ];
			System.arraycopy(Constant.LETTER_ARR, 0, charArr, 0, Constant.LETTER_ARR.length);
		} else if (type == TYPE_MIX){
			charArr = new String[Constant.NUMBER_ARR.length + Constant.LETTER_ARR.length ];
			System.arraycopy(Constant.NUMBER_ARR, 0, charArr, 0, Constant.NUMBER_ARR.length);
			System.arraycopy(Constant.LETTER_ARR, 0, charArr, Constant.NUMBER_ARR.length, Constant.LETTER_ARR.length);
		} else {
			throw new Exception("没有这个类型:"+type);
		}
		for (int i=0;i<length;i++){
			int randomIndex = (int) Math.floor(Math.random()*(charArr.length));
			sb.append(charArr[randomIndex]);
		}
		return sb.toString();
	}

	public static String strToUnicode(String str){
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<str.length();i++){
			char c = str.charAt(i);
			sb.append(Constant.SYMBOL_UNICODE_PREFIX);
			String u = Integer.toHexString(c);
			sb.append(u);
		}
		return sb.toString();
	}

	public static String unicodeToStr(String unicode){
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile(Constant.REGEX_UNICODE);
		Matcher m = p.matcher(unicode);
		while (m.find()){
			String u = m.group();
			char letter = (char) Integer.parseInt(u.substring(2), 16);
			sb.append(letter);
		}
		return sb.toString();
	}

	public static String mapToString(Map map){
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()){
			Entry entry = (Entry) iterator.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof Map){
				mapToString((Map) value);
			} else {
				System.out.println(key.toString() + ":" + value.toString());
			}
		}
		return null;
	}

	public static String encode(String str,String encode) {
		String output = "";
		try {
			byte[]bt= str.getBytes();
			output = new String(bt,encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return output;
	}
}
