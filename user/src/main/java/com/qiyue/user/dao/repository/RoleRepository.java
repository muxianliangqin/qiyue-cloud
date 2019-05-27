package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.RightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RightEntity, String> {
}
