package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.UserRightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRightRepository extends JpaRepository<UserRightEntity, String> {
}