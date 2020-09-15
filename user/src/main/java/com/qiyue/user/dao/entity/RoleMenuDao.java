package com.qiyue.user.dao.entity;

import com.qiyue.user.entity.RoleMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleMenuDao extends JpaRepository<RoleMenuEntity, Long> {

    List<RoleMenuEntity> findByRoleId(Long roleId);

    Optional<RoleMenuEntity> findByRoleIdAndMenuId(Long roleId, Long menuId);

    void deleteByRoleIdAndMenuId(Long roleId, Long menuId);

}
