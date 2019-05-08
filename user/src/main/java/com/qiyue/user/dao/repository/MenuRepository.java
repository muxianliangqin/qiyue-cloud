package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, String> {
    @Query(value = "select DISTINCT(m.menu_id),m.code,m.name,m.super_code,m.url,m.menu_desc, \n" +
            "m.menu_state,m.create_time,m.create_user,m.update_time,m.update_user\n" +
            "from user_info u \n" +
            "\tjoin user_ref_role ur on u.user_id = ur.user_id and u.user_id = ?1\n" +
            "\tjoin role_info r on ur.role_code = r.code\n" +
            "\tjoin role_ref_menu rm on r.code = rm.role_code \n" +
            "\tjoin menu_info m on rm.menu_code = m.code",nativeQuery = true)
    public List<MenuEntity> getMenus(int id);
}
