package com.qiyue.dao.repo;

import com.qiyue.dao.entity.RecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<RecordEntity, Integer> {


    Page<RecordEntity> findByGroupId(int groupId, Pageable pageable);

}
