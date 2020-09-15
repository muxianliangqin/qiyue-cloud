package com.qiyue.crawler.dao.entity;

import com.qiyue.crawler.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDao extends JpaRepository<FileEntity, Long> {

    Optional<FileEntity> findByFileId(Long fileId);

}
