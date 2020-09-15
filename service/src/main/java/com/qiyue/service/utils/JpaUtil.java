package com.qiyue.service.utils;

import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.DatabaseException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Consumer;

public class JpaUtil {

    public static <T, ID, V> T update(JpaRepository<T, ID> jpaRepository, ID id, Consumer<V> setField, V v) {
        T t = jpaRepository.findById(id).orElseThrow(() ->
            new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "id", id)
        );
        setField.accept(v);
        return jpaRepository.save(t);
    }

    private static  <V> void setField(Consumer<V> consumer, V t) {
        consumer.accept(t);
    }

}
