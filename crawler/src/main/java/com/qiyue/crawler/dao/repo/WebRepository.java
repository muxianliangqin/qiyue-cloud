package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.WebEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebRepository extends JpaRepository<WebEntity,Integer> {
    Page<WebEntity> findByUserIdAndState(int userId,
                                         String state,
                                         Pageable pageable);
}
