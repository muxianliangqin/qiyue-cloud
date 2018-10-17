package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsEntity,Integer> {

    List<NewsEntity> findByCategoryUrlAndState(String categoryUrl,String state);

    long countByCategoryUrlAndState(String categoryUrl,String state);
}
