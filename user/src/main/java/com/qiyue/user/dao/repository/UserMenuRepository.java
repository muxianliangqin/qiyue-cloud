package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.UserMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMenuRepository extends JpaRepository<UserMenuEntity, Integer> {

    List<UserMenuEntity> findByUserId(int userId);

    int deleteByMenuCodeIn(List<Integer> ids);
}
