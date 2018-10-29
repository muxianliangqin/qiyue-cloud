package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.NewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity,Integer> {

    Page<NewsEntity> findByCategoryUrlAndState(String categoryUrl,String state,Pageable pageable);

    long countByCategoryUrlAndState(String categoryUrl,String state);

}
