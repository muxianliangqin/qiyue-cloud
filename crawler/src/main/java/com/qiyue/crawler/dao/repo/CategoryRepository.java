package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {

    @Modifying
    @Query("update CategoryEntity set state = '1' where id = ?1")
    int updateState(int id);

    @Modifying
    @Query(value = "update category set category_state = '1' where web_id = ?1",
            nativeQuery = true)
    int updateStateByWebId(int webId);

    @Modifying
    @Query(value = "insert into category (title, url, xpath, web_id) values (:title, :url, :xpath, :webId)",
            nativeQuery = true)
    int add(@Param("title") String title,
            @Param("url") String url,
            @Param("xpath") String xpath,
            @Param("webId") int webId);

    Optional<CategoryEntity> findByUrl(String url);
}
