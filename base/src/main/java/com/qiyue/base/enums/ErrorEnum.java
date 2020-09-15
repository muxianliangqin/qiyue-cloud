package com.qiyue.base.enums;

/**
 * 本枚举中每一个枚举都与ErrorConstant一一对应，以便于错误码与错误信息的管理
 * A0001-程序错误，application error
 *      程序代码主动俘获的错误，如FileNotFoundException等错误
 * P0001-参数错误，model error
 *      参数输入的错误
 * D0001-数据库错误，database error
 *      数据数据缺失、冗余等错误
 * S0001-系统相关错误，system error
 *      系统运行未预料的错误
 */
public enum ErrorEnum {
    SUCCESS("00000", "success"),

    LOGIN_ERROR("P0000", "用户名或密码不正确"),
    LOGIN_MULTI_ERROR("P0001", "同一个浏览器只允许一个用户登录"),
    TOKEN_ERROR("P0002", "token码错误"),
    // 第一个参数是字段描述，第二个是字段name
    PARAM_CANNOT_NULL("P0003", "参数【{}：{}】不能为空"),
    PARAM_LIST_CANNOT_EMPTY("P0004", "参数【{}：{}】不能为空"),
    PARAM_INVALID("P0005", "参数【{}：{}】不是其中一个【{}】"),

    // 第一个参数是关键字name，第二个是关键字value
    RECORD_NOT_FOUND("D0100", "记录【{}：{}】不存在"),
    RECORD_HAS_EXIST("D0101", "记录【{}：{}】已存在"),

    INSERT_BATCH_ERROR("D0200", "批量插入失败"),
    INSERT_EMPTY_INPUT_ERROR("D0201", "批量插入输入参数错误"),

    /* I/O分类 */
    FILE_NOT_FOUND("A0100", "文件不存在"),
    /* 反射分类 */
    ILLEGAL_ACCESS("A0200", "非法进入【{}：{}】"),
    NO_SUCH_FIELD("A0201", "没有这个字段【{}】"),

    /* 加解密分类 */
    // 第一个参数是 加密方法名称
    NO_SUCH_ALGORITHM("A0300", "没有这种【{}】加密算法"),

    /* 自定义类 */
    RANDOM_STRING_NO_SUCH_TYPE("A1000", "生成随机字符串没有这个类型：{}"),
    /* 转换TreeNode相关的错误 */
    ELEMENT_NOT_NULL_DATA("A1100", "转换树节点元素时，值不能为空"),
    ELEMENT_NOT_NULL_NODE_ID("A1101", "转换树节点元素时，nodeId的值不能为空"),

    /*枚举转换映射相关的错误 */
    // 第一个参数枚举名称，第二参数枚举值属性，第三个参数 当前值
    ENUM_VALUE_NOT_FOUND("A1200", "枚举对象【{}:{}】没有这个值【{}】"),

    /*通用分类 */
    UNKNOWN_ERROR("S9999", "系统未知错误");

    private String code;

    private String msg;

    ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
