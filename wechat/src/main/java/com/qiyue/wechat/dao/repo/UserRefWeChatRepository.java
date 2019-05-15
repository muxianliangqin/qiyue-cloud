package com.qiyue.wechat.dao.repo;

import com.qiyue.wechat.dao.entity.UserRefWeChatEntity;
import com.qiyue.wechat.dao.entity.WeChatRecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRefWeChatRepository extends JpaRepository<UserRefWeChatEntity,Integer> {

    List<UserRefWeChatEntity> findByUserIdAndState(int userId, String state);

    Page<UserRefWeChatEntity> findByUserIdAndState(int userId,
                                                  String state,
                                                  Pageable pageable);

    long countByUserIdAndState(int userId,String state);

    Optional<UserRefWeChatEntity> findById(int id);

    @Modifying
    @Query("update UserRefWeChatEntity set state = '1' where id = ?1")
    int updateState(int id);

    UserRefWeChatEntity saveAndFlush(UserRefWeChatEntity userRefWeChatEntity);
}
