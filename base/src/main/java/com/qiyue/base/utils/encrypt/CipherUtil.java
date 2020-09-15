package com.qiyue.base.utils.encrypt;

import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.BusinessException;
import org.springframework.security.crypto.codec.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加解密
 */
public class CipherUtil {

    private static final String ALGORITHM_SHA = "SHA";
    private static final String ALGORITHM_MD5 = "MD5";

    public static final Encrypt<String, String> MD5 = (plainText) -> {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(ALGORITHM_MD5);
            byte byteArray[] = plainText.getBytes(StandardCharsets.UTF_8);
            byte md5Bytes[] = md5.digest(byteArray);
            StringBuilder hexValue = new StringBuilder();
            for (byte bt: md5Bytes) {
                if (bt < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(bt));
            }
            return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e, ErrorEnum.NO_SUCH_ALGORITHM, ALGORITHM_MD5);
        }
    };

    public static final SaltEncrypt<String, String, String> SHA = (plainText, salt) -> {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(ALGORITHM_SHA);
            byte[] bytes = (plainText + salt).getBytes(StandardCharsets.UTF_8);
            byte[] resultBytes = messageDigest.digest(bytes);
            char[] chars = Hex.encode(resultBytes);
            return String.valueOf(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BusinessException(e, ErrorEnum.NO_SUCH_ALGORITHM, ALGORITHM_SHA);
        }
    };
}
