package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    List<CategoryEntity> findByState(String state);
}
