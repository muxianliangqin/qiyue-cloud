package com.qiyue.base.constant;

public class Constant {
    public static final String AMOUNT_FORMAT = "[0-9]+(.[0-9]{1,2})?";//金额格式
    public static final String IS_INT = "-?[0-9]+"; //是否是int数字
    public static final String[] NUMBER_ARR = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public static final String[] LETTER_ARR = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
            "v", "w", "x", "y", "z"};
    public static final int TYPE_NUMBER = 0;//数字
    public static final int TYPE_LETTER = 1;//字母
    public static final int TYPE_MIX = 2;//混合
    //符号
    public static final String SYMBOL_POUND_KEY = "#";//  符号：#
    public static final String SYMBOL_DOLLAR = "$";//  符号：$
    public static final String SYMBOL_URL_LEVEL_UP = "../";//  符号：../
    public static final String SYMBOL_URL_LEVEL_SAME = "./";//  符号：./
    public static final String SYMBOL_SEPARATOR = "/";//  符号：/
    public static final String SYMBOL_BACKSLASH = "\\";//  符号：\
    public static final String SYMBOL_UNICODE_PREFIX = "\\u";//  符号：\ u
    public static final String SYMBOL_SPACE = " ";//  符号：空格
    public static final String SYMBOL_NULL_STRING = "";//  符号：空字符串
    public static final String SYMBOL_DOT = ".";//  符号：点
    public static final String SYMBOL_COMMA = ",";//  符号：英文逗号

    //正则表达式
    public static final String REGEX_VERTICAL_BAR = "\\|"; //  正则表达式：|
    public static final String REGEX_DOUBLE_VERTICAL_LINES = "\\|\\|";//  正则表达式：||
    public static final String REGEX_URL_LEVEL = "\\.{0,2}\\/";//  正则表达式：url层级
    public static final String REGEX_URL_LEVEL_UP = "\\.\\.\\/";//  正则表达式：../
    public static final String REGEX_URL_LEVEL_SAME = "\\.\\/";//  正则表达式：./
    public static final String REGEX_SEPARATOR = "\\/";//  正则表达式：/
    public static final String REGEX_SPACE = "\\s";//  正则表达式：空格
    public static final String REGEX_DOT = "\\.";//  正则表达式：圆点
    public static final String REGEX_URL_WEBSITE = "http(s?)\\:\\/\\/[^\\/]*";//  正则表达式：域名url
    public static final String REGEX_URL_ABSOLUTE = "http(s?)\\:\\/\\/[A-z0-9-_\\.~!\\*'\\(\\);:@&=\\+\\$,\\/\\?#\\[\\]]*";// 正则表达式：绝对url
    public static final String REGEX_URL_RELATIVE = "((\\/)|(\\.\\/)|(\\.\\.\\/))[\\/\\._A-z0-9]*";//  正则表达式：相对url
    public static final String REGEX_URL = "(http(s)?\\:\\/\\/)?[A-z0-9-_\\.&=\\/\\?]*";//  正则表达式：url
    public static final String REGEX_URL_HTML = ".*\\.[A-z]*$";//  正则表达式：静态网页
    public static final String REGEX_URL_OTHER = ".*\\.(?i)(js|css|jpg|png|doc|gif)$";//  正则表达式：静态网页
    public static final String REGEX_WEBSITE_LINK = "(href|src)=('|\")[^>|\\s|;|#|:]*('|\")";//  正则表达式：网页中的链接
    public static final String REGEX_URL_LEVEL_NAME = "\\/[^\\/]*";//  正则表达式：url层级代码
    public static final String REGEX_CSS = ".*\\.css$";//  正则表达式：css静态资源
    public static final String REGEX_JS = ".*\\.js$";//  正则表达式：js静态资源
    public static final String REGEX_HANZI = "[\u4e00-\u9fa5]+";//  正则表达式：汉字
    public static final String REGEX_UNICODE = "\\\\u([^\\\\]{2,4})";// 正则表达式：单个unicode编码
    public static final String REGEX_DATE_NO_INTERVAL = "[0-9]{4}[0-9]{1,2}[0-9]{1,2}[0-9]{2}[0-9]{2}[0-9]{2}";//yyyyMMddHHmmss
    public static final String REGEX_DATE_WITH_HYPHEN = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}";//yyyy-MM-dd hh:mm:ss
    public static final String REGEX_DATE_CHINESE_NO_TIME = "[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日";//yyyy年MM月dd日
    public static final String REGEX_DATE_WITH_HYPHEN_NO_TIME = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";//yyyy-MM-dd

    public static final String REGEX_STRING_FORMAT_PLACEHOLDER = "\\{}";

    //编码
    public static final String ENCODE_UTF8 = "UTF-8"; // 编码：utf-8
    public static final String ENCODE_GBK = "GBK"; // 编码：GBK

    //日期
    public static final String DATE_FORMATER_NO_INTERVAL = "yyyyMMddHHmmss";
    public static final String DATE_FORMATER_NO_INTERVAL_NO_TIME = "yyyyMMdd";
    public static final String DATE_FORMATER_WITH_HYPHEN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATER_CHINESE_NO_TIME = "yyyy年MM月dd日";
    public static final String DATE_FORMATER_WITH_HYPHEN_NO_TIME = "yyyy-MM-dd";

    // session - user
    // 弃用，改为gateway
    public static final String SESSION_USER = "user";
    public static final String COOKIE_SESSION_ID = "sessionId";
    public static final String SESSION_REDIS_NAME_SPACE = "user";
    public static final String SESSION_REDIS_KEY_PREFIX = SESSION_USER + ":sessions:";
    public static final String SESSION_REDIS_EXPIRE_KEY_PREFIX = SESSION_USER + ":sessions:expires:";

    // 使用token验证用户登录
    public static final String TOKEN_NAMESPACE = "token";
    public static final int TOKEN_LENGTH = 20;
    public static final String COOKIE_USER_NAMESPACE = "user";

    // IO流读写时bytes的缓存大小
    public static final int IO_BYTES_BUFFER_SIZE = 64 * 1024;
    // 用户登出时，返回http状态码：700
    public static final int LOGOUT_HTTP_STATUS = 700;
}
