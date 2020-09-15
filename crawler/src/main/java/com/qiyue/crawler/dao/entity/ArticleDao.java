package com.qiyue.crawler.dao.entity;

import com.qiyue.crawler.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleDao extends JpaRepository<ArticleEntity, Long>, JpaSpecificationExecutor {
    Optional<ArticleEntity> findByUrl(String url);
}
