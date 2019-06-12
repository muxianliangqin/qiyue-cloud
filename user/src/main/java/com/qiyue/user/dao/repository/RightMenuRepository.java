package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.RightMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RightMenuRepository extends JpaRepository<RightMenuEntity, Integer> {

    List<RightMenuEntity> findByRightCode(String rightCode);

}