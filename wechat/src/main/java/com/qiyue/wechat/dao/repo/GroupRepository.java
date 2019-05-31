package com.qiyue.wechat.dao.repo;

import com.qiyue.wechat.dao.entity.GroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupEntity,Integer> {

    Page<GroupEntity> findByUserIdAndState(int userId,
                                           String state,
                                           Pageable pageable);

    Optional<GroupEntity> findById(int id);

    @Modifying
    @Query("update GroupEntity set state = '1' where id = ?1")
    int updateState(int id);

    @Modifying
    @Query(value="insert into groups (user_id,group_nick_name) values (?1,?2)",
            nativeQuery=true)
    int add(int userId, String groupNickName);

    @Modifying
    @Query("update GroupEntity set groupNickName = :groupNickName where id = :id")
    int modify(@Param("id") int id,
               @Param("groupNickName") String groupNickName);
}
