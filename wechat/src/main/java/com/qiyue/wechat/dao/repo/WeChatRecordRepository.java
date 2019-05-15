package com.qiyue.wechat.dao.repo;

import com.qiyue.wechat.dao.entity.WeChatRecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeChatRecordRepository extends JpaRepository<WeChatRecordEntity,Integer> {


    Page<WeChatRecordEntity> findByGroupNickName(String groupNickName,
                                                 Pageable pageable);

    long countByGroupNickName(String groupNickName);

}
