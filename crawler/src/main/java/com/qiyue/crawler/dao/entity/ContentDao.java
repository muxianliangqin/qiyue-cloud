package com.qiyue.crawler.dao.entity;

import com.qiyue.crawler.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentDao extends JpaRepository<ContentEntity, Long> {
    ContentEntity findByContentId(Long contentId);
}
