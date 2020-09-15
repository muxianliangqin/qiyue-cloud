package com.qiyue.base.utils.encrypt;

/**
 * 加盐哈希加密算法
 *
 * @param <T> 明文
 * @param <S> 随机干扰项
 * @param <R> 密文
 */
@FunctionalInterface
public interface SaltEncrypt<T, S, R> {

    R encrypt(T t, S s);
}
