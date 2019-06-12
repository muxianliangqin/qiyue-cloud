package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.RightEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RightRepository extends JpaRepository<RightEntity, Integer> {

    Page<RightEntity> findAll(Pageable pageable);

    @Query(value = "update rights set right_state = '1' where id = :id",
            nativeQuery = true)
    @Modifying
    int stop(@Param("id") int id);

    @Query(value = "update rights set right_state = '0' where id = :id",
            nativeQuery = true)
    @Modifying
    int restart(@Param("id") int id);

    @Query(value = "insert into rights (code,name,right_desc) " +
            "values (:code,:name,:desc)",
            nativeQuery = true)
    @Modifying
    int add(@Param("code") String code,
            @Param("name") String name,
            @Param("desc") String desc);
}
