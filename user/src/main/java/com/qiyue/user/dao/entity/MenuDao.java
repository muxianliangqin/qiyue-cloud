package com.qiyue.user.dao.entity;

import com.qiyue.user.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuDao extends JpaRepository<MenuEntity, Long> {

    @Query(value = "select m.id, m.menu_id, m.super_menu_id, m.`name`, m.url, m.`desc`, m.component_name, " +
            "m.sort_no, m.type, m.operate_type, " +
            "m.state, m.create_time, m.create_user, m.update_time, m.update_user " +
            "from `user` u " +
            "join user_role ur on u.user_id = ur.user_id and u.state = 0 and ur.state = 0 " +
            "join role r on ur.role_id = r.role_id and r.state = 0 " +
            "join role_menu rm on rm.role_id = r.role_id and rm.state = 0 " +
            "join menu m on m.menu_id = rm.menu_id and m.state = 0 " +
            "where u.user_id = :userId " +
            "order by m.rank_no asc, m.sort_no asc", nativeQuery = true)
    List<MenuEntity> getMenusByUserId(@Param(value = "userId") Long userId);

    @Query(value = "select m.id, m.menu_id, m.super_menu_id, m.`name`, m.url, m.`desc`, m.component_name, " +
            "m.rank_no, m.sort_no, m.type, m.operate_type" +
            "m.state, m.create_time, m.create_user, m.update_time, m.update_user " +
            "from role r " +
            "join role_menu rm on rm.role_id = r.role_id and rm.state = 0 " +
            "join menu m on m.menu_id = rm.menu_id and m.state = 0 " +
            "where r.role_id in :roleIds " +
            "order by m.rank_no asc, m.sort_no asc", nativeQuery = true)
    List<MenuEntity> getMenusByRoleIds(@Param(value = "roleIds") List<Long> roleIds);

    Optional<MenuEntity> findByMenuId(Long menuId);

    List<MenuEntity> findBySuperMenuId(Long superMenuId);
}
