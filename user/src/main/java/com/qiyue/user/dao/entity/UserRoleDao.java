package com.qiyue.user.dao.entity;

import com.qiyue.user.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleDao extends JpaRepository<UserRoleEntity, Long> {

    List<UserRoleEntity> findByUserIdAndState(Long userId, Integer state);
}
