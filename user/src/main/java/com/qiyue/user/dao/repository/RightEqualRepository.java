package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.RightEqualEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RightEqualRepository extends JpaRepository<RightEqualEntity, Integer> {

    List<RightEqualEntity> findByUserIdAndState(int userId, String state);

}
