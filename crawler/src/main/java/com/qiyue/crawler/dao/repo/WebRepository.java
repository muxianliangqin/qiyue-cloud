package com.qiyue.crawler.dao.repo;

import com.qiyue.crawler.dao.entity.WebEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebRepository extends JpaRepository<WebEntity,Integer> {
    List<WebEntity> findByState(String state);
}
