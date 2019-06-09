package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.NewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NewsRepository extends JpaRepository<NewsEntity,Integer> {

    Page<NewsEntity> findByCategoryIdAndState(int categoryId, String state, Pageable pageable);

    @Modifying
    @Query(value = "update news set unread = '1',update_time = update_time where id = ?1",nativeQuery = true)
    int hasRead(int id);
}
