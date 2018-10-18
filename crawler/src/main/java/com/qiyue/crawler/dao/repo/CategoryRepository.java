package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    List<CategoryEntity> findByState(String state);

    @Modifying
    @Query("update CategoryEntity set state = '1' where id = ?1")
    int updateState(int id);

    CategoryEntity saveAndFlush(CategoryEntity categoryEntity);
}
