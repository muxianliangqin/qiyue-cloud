package com.qiyue.wechat.dao.repo;

import com.qiyue.wechat.dao.entity.WechatRecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WechatRecordRepository extends JpaRepository<WechatRecordEntity,Integer> {


    Page<WechatRecordEntity> findByGroupNickNameAndState(String groupNickName,
                                                         String state,
                                                         Pageable pageable);

    long countByGroupNickNameAndState(String groupNickName,
                                      String state);

}
