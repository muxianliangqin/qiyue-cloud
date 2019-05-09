package com.qiyue.wechat.dao.repo;

import com.qiyue.wechat.dao.entity.UserRefWechatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRefWechatRepository extends JpaRepository<UserRefWechatEntity,Integer> {

    List<UserRefWechatEntity> findByUserIdAndState(String userId, String state);

    Optional<UserRefWechatEntity> findById(int id);

    @Modifying
    @Query("update UserRefWechatEntity set state = '1' where id = ?1")
    int updateState(int id);

    UserRefWechatEntity saveAndFlush(UserRefWechatEntity userRefWechatEntity);
}
