package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.WebEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WebRepository extends JpaRepository<WebEntity,Integer> {
    Page<WebEntity> findByUserIdAndState(int userId,
                                         String state,
                                         Pageable pageable);

    @Modifying
    @Query("update WebEntity set state = '1' where id = ?1")
    int updateState(int id);

    @Modifying
    @Query(value = "insert into web (title, url, user_id) values (:title, :url, :userId)",
            nativeQuery = true)
    int add(@Param("title") String title,
            @Param("url") String url,
            @Param("userId") int userId);

    Optional<WebEntity> findByUrl(String url);
}
