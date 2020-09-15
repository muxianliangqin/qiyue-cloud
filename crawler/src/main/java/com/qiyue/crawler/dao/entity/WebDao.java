package com.qiyue.crawler.dao.entity;

import com.qiyue.crawler.entity.WebEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WebDao extends JpaRepository<WebEntity, Long> {
    Page<WebEntity> findAllByState(Integer state, Pageable pageable);

    Optional<WebEntity> findByUrl(String url);

    Optional<WebEntity> findByWebId(Long webId);

}
