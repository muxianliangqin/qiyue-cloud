package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, String> {
    @Query(value = "select distinct(m.id),m.code,m.name,m.super_code,m.url,m.menu_desc, \n" +
            "            m.menu_state,m.create_time,m.create_user,m.update_time,m.update_user,m.xpath\n" +
            "   from menu m \n" +
            "   join right_menu rm on rm.menu_code = m.code\n" +
            "   join rights r on r.code = rm.right_code\n" +
            "   join user_right ur on ur.right_code = r.code\n" +
            "   join user u on u.id = ur.user_id\n" +
            "   where m.menu_state = '0' " +
            "   and u.id = ?1", nativeQuery = true)
    public List<MenuEntity> getMenus(int userId);
}
