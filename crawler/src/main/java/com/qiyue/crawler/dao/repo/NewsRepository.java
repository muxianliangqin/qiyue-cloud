package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsEntity,Integer> {

    List<NewsEntity> findByCategoryUrl(String categoryUrl);
}
