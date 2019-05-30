package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.RightEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RightRepository extends JpaRepository<RightEntity, String> {

    Page<RightEntity> findAll(Pageable pageable);
}
