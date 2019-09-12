package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.KeywordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KeywordRepository extends JpaRepository<KeywordEntity, Integer> {

    @Modifying
    @Query(value = "insert into keyword (name, reg_exp, reg_codes, user_id) " +
            "values (:name, :regexp, :codes, :userId)",
            nativeQuery = true)
    int add(@Param("name") String name,
            @Param("regexp") String regexp,
            @Param("codes") String codes,
            @Param("userId") int userId);

    @Modifying
    @Query(value = "update keyword set name = :name, reg_exp = :regexp, " +
            "reg_codes = :codes where id=:id",
            nativeQuery = true)
    int modify(@Param("name") String name,
               @Param("regexp") String regexp,
               @Param("codes") String codes,
               @Param("id") int id);

    Page<KeywordEntity> findAllByUserId(int userId, Pageable pageable);

    List<KeywordEntity> findAllByUserId(int userId);
}
