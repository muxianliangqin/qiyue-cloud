package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.AttachmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentsRepository extends JpaRepository<AttachmentsEntity, Integer> {
}
