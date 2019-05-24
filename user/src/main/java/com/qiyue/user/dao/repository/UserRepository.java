package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByMobileAndPassword(String mobile, String password);

    Optional<UserEntity> findByMobile(String mobile);

    Optional<UserEntity> findByToken(String token);
}
