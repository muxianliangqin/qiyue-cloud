package com.qiyue.wechat.constant;

public class Constant {
	public final static String AMOUNT_FORMAT = "[0-9]+(.[0-9]{1,2})?";//金额格式
	public final static String IS_INT = "-?[0-9]+"; //是否是int数字
	public final static String[] NUMBER_ARR = {"0","1","2","3","4","5","6","7","8","9"};
	public final static String[] LETTER_ARR = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S",
			"T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u",
			"v","w","x","y","z"};

	//符号
	public final static String SYMBOL_POUND_KEY = "#";//  符号：#
	public final static String SYMBOL_DOLLAR = "$";//  符号：$
	public final static String SYMBOL_URL_LEVEL_UP = "../";//  符号：../
	public final static String SYMBOL_URL_LEVEL_SAME = "./";//  符号：./
	public final static String SYMBOL_SEPARATOR = "/";//  符号：/
	public final static String SYMBOL_BACKSLASH = "\\";//  符号：\
	public final static String SYMBOL_UNICODE_PREFIX = "\\u";//  符号：\ u
	public final static String SYMBOL_SPACE = " ";//  符号：空格
	public final static String SYMBOL_NULL_STRING = "";//  符号：空字符串
	public final static String SYMBOL_DOT = ".";//  符号：空字符串

	//正则表达式
	public final static String REGEX_VERTICAL_BAR = "\\|"; //  正则表达式：|
	public final static String REGEX_DOUBLE_VERTICAL_LINES = "\\|\\|";//  正则表达式：||
	public final static String REGEX_URL_LEVEL = "\\.{0,2}\\/";//  正则表达式：url层级
	public final static String REGEX_URL_LEVEL_UP = "\\.\\.\\/";//  正则表达式：../
	public final static String REGEX_URL_LEVEL_SAME = "\\.\\/";//  正则表达式：./
	public final static String REGEX_SEPARATOR = "\\/";//  正则表达式：/
	public final static String REGEX_SPACE = "\\s";//  正则表达式：空格
	public final static String REGEX_DOT = "\\.";//  正则表达式：圆点
	public final static String REGEX_URL_WEBSITE = "http(s?)\\:\\/\\/[^\\/]*";//  正则表达式：域名url
	public final static String REGEX_URL_ABSOLUTE = "http(s?)\\:\\/\\/[A-z0-9-_\\.~!\\*'\\(\\);:@&=\\+\\$,\\/\\?#\\[\\]]*";// 正则表达式：绝对url
	public final static String REGEX_URL_RELATIVE = "((\\/)|(\\.\\/)|(\\.\\.\\/))[\\/\\._A-z0-9]*";//  正则表达式：相对url
	public final static String REGEX_URL = "(http(s)?\\:\\/\\/)?[A-z0-9-_\\.&=\\/\\?]*";//  正则表达式：url
	public final static String REGEX_URL_HTML = ".*\\.[A-z]*$";//  正则表达式：静态网页
	public final static String REGEX_URL_OTHER = ".*\\.(?i)(js|css|jpg|png|doc|gif)$";//  正则表达式：静态网页
	public final static String REGEX_WEBSITE_LINK = "(href|src)=('|\")[^>|\\s|;|#|:]*('|\")";//  正则表达式：网页中的链接
	public final static String REGEX_URL_LEVEL_NAME = "\\/[^\\/]*";//  正则表达式：url层级代码
	public final static String REGEX_CSS = ".*\\.css$";//  正则表达式：css静态资源
	public final static String REGEX_JS = ".*\\.js$";//  正则表达式：js静态资源
	public final static String REGEX_HANZI = "[\u4e00-\u9fa5]+";//  正则表达式：汉字
	public final static String REGEX_UNICODE = "\\\\u([^\\\\]{2,4})";// 正则表达式：单个unicode编码
	public final static String REGEX_DATE_NO_INTERVAL = "[0-9]{4}[0-9]{1,2}[0-9]{1,2}[0-9]{2}[0-9]{2}[0-9]{2}";//yyyyMMddHHmmss
	public final static String REGEX_DATE_WITH_HYPHEN = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}";//yyyy-MM-dd hh:mm:ss
	public final static String REGEX_DATE_CHINESE_NO_TIME = "[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日";//yyyy年MM月dd日
	public final static String REGEX_DATE_WITH_HYPHEN_NO_TIME = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";//yyyy-MM-dd

	//编码
	public final static String ENCODE_UTF8 = "UTF-8"; // 编码：utf-8
	public final static String ENCODE_GBK = "GBK"; // 编码：GBK

	//日期
	public final static String DATE_FORMATER_NO_INTERVAL = "yyyyMMddHHmmss";
	public final static String DATE_FORMATER_NO_INTERVAL_NO_TIME = "yyyyMMdd";
	public final static String DATE_FORMATER_WITH_HYPHEN = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE_FORMATER_CHINESE_NO_TIME = "yyyy年MM月dd日";
	public final static String DATE_FORMATER_WITH_HYPHEN_NO_TIME = "yyyy-MM-dd";
}
