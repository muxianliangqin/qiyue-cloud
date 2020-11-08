package com.qiyue.crawler.dao.entity;

import com.qiyue.crawler.entity.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColumnDao extends JpaRepository<ColumnEntity, Long> {

    Optional<ColumnEntity> findByUrl(String url);

    Optional<ColumnEntity> findByColumnId(Long columnId);

    List<ColumnEntity> findByTitleLike(String title);

    int deleteByWebId(Long webId);

    List<ColumnEntity> findByWebIdAndState(Long webId, Integer state);

    List<ColumnEntity> findByStateOrderByCrawlTimeAsc(Integer state);
}
