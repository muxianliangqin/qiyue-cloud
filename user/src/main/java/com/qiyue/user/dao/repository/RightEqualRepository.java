package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.MenuLoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RightEqualRepository extends JpaRepository<MenuLoanEntity, Integer> {

    List<MenuLoanEntity> findByUserIdAndState(int userId, String state);

}
