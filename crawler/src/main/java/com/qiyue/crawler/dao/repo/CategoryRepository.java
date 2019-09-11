package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    @Modifying
    @Query("update CategoryEntity set state = '1' where id = ?1")
    int updateState(int id);

    @Modifying
    @Query(value = "update category set new_num = 0, update_time = update_time where id = ?1", nativeQuery = true)
    int hasRead(int id);

    @Modifying
    @Query(value = "update category set category_state = '1' where web_id = ?1",
            nativeQuery = true)
    int updateStateByWebId(int webId);

    @Modifying
    @Query(value = "insert into category (title, url, xpath_title, xpath_text, charset, web_id) " +
            "values (:title, :url, :xpathTitle, :xpathText, :charset, :webId)",
            nativeQuery = true)
    int add(@Param("title") String title,
            @Param("url") String url,
            @Param("xpathTitle") String xpathTitle,
            @Param("xpathText") String xpathText,
            @Param("charset") String charset,
            @Param("webId") int webId);

    Optional<CategoryEntity> findByUrl(String url);
}
