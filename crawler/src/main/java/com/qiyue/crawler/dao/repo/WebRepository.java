package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.WebEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WebRepository extends JpaRepository<WebEntity,Integer> {
    Page<WebEntity> findByUserIdAndState(int userId,
                                         String state,
                                         Pageable pageable);

    @Modifying
    @Query("update WebEntity set state = '1' where id = ?1")
    int updateState(int id);
}
