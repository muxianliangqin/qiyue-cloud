package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.UserMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMenuRepository extends JpaRepository<UserMenuEntity, String> {
}
