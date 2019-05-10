package com.qiyue.wechat.dao.repo;

import com.qiyue.wechat.dao.entity.UserRefWeChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRefWeChatRepository extends JpaRepository<UserRefWeChatEntity,Integer> {

    List<UserRefWeChatEntity> findByUserIdAndState(int userId, String state);

    Optional<UserRefWeChatEntity> findById(int id);

    @Modifying
    @Query("update UserRefWeChatEntity set state = '1' where id = ?1")
    int updateState(int id);

    UserRefWeChatEntity saveAndFlush(UserRefWeChatEntity userRefWeChatEntity);
}
