package com.qiyue.user.dao.vo;

import com.qiyue.user.entity.MenuEntity;
import com.qiyue.user.model.vo.MenuVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuVODao extends JpaRepository<MenuVO, Long> {

    @Query(value = "select distinct m.menu_id, m.super_menu_id, m.`name`, m.component_name, m.`desc`, " +
            "m.type, m.operate_type, m.icon, m.state, rm.role_id, rm.permission_type, " +
            "m.rank_no, m.sort_no " +
            "from `user` u " +
            "join user_role ur on u.user_id = ur.user_id and u.state = 0 and ur.state = 0 " +
            "join role r on ur.role_id = r.role_id and r.state = 0 " +
            "join role_menu rm on rm.role_id = r.role_id and rm.state = 0 " +
            "join menu m on m.menu_id = rm.menu_id and m.state = 0 " +
            "where u.user_id = :userId " +
            "order by m.rank_no asc, m.sort_no asc, rm.permission_type asc", nativeQuery = true)
    List<MenuVO> getMenusByUserId(@Param(value = "userId") Long userId);

    @Query(value = "select distinct m.menu_id, m.super_menu_id, m.`name`, m.component_name, m.`desc`, " +
            "m.type, m.operate_type, m.icon, m.state, rm.role_id, rm.permission_type, " +
            "m.rank_no, m.sort_no " +
            "from role r " +
            "join role_menu rm on rm.role_id = r.role_id and rm.state = 0 " +
            "join menu m on m.menu_id = rm.menu_id and m.state = 0 " +
            "where r.role_id in :roleIds " +
            "order by m.rank_no asc, m.sort_no asc, rm.permission_type asc", nativeQuery = true)
    List<MenuVO> getMenusByRoleIds(@Param(value = "roleIds") List<Long> roleIds);

}
