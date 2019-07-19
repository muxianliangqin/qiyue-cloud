package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.RegexpsEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegexpsRepository extends JpaRepository<RegexpsEntity, Integer> {
    List<RegexpsEntity> findAll(Sort sort);
}
