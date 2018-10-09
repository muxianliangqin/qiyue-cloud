package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
}
