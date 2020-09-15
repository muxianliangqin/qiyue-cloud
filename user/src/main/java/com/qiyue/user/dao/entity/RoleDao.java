package com.qiyue.user.dao.entity;

import com.qiyue.user.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleDao extends JpaRepository<RoleEntity, Long> {

    /**
     * 批量查询角色记录
     *
     * @param roleIds 角色id集合
     * @param state   记录状态
     * @return 角色记录集合
     */
    List<RoleEntity> findByRoleIdInAndState(List<Long> roleIds, Integer state);

    Page<RoleEntity> findAll(Pageable pageable);

    @Query(value = "select r " +
            "from UserEntity u " +
            "join UserRoleEntity ur on u.userId = ur.userId and u.state = 0 and ur.state = 0 " +
            "join RoleEntity r on ur.roleId = r.roleId and r.state = 0 " +
            "where u.userId = :userId")
    List<RoleEntity> findByUserId(@Param(value = "userId") Long userId);

    Optional<RoleEntity> findByRoleId(Long roleId);

    Optional<RoleEntity> findByCode(String code);
}
