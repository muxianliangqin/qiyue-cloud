package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByMobileAndPassword(String mobile, String password);

    Optional<UserEntity> findByMobile(String mobile);

    Optional<UserEntity> findByToken(String token);

    Page<UserEntity> findAll(Pageable pageable);

    @Query(value = "update user set user_state = '1' where id = :id",
            nativeQuery = true)
    @Modifying
    int stop(@Param("id") int id);

    @Query(value = "update user set user_state = '0' where id = :id",
            nativeQuery = true)
    @Modifying
    int restart(@Param("id") int id);

    @Query(value = "insert into user (username,mobile,password,alias,gender,salt) " +
            "values (:username,:mobile,:password,:alias,:gender,:salt)",
            nativeQuery = true)
    @Modifying
    int add(@Param("username") String username,
            @Param("mobile") String mobile,
            @Param("password") String password,
            @Param("salt") String salt,
            @Param("alias") String alias,
            @Param("gender") String gender);
}
