package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.RightMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRefMenuRepository extends JpaRepository<RightMenuEntity, String> {
}
