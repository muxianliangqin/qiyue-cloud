package com.qiyue.user.dao.entity;

import com.qiyue.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByMobile(String mobile);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUserId(Long userId);

    Optional<UserEntity> findByToken(String token);

    void deleteByUserId(Long userId);

    Page<UserEntity> findAll(@NotNull Pageable pageable);

    List<UserEntity> findByUsernameLike(String username);
}
