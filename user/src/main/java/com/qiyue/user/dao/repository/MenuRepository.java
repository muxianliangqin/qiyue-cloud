package com.qiyue.user.dao.repository;

import com.qiyue.user.dao.entity.MenuEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {
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

    Page<MenuEntity> findAll(Pageable pageable);

    @Query(value = "update menu set menu_state = '1' where id = :id",
            nativeQuery = true)
    @Modifying
    int stop(@Param("id") int id);

    @Query(value = "update menu set menu_state = '0' where id = :id",
            nativeQuery = true)
    @Modifying
    int restart(@Param("id") int id);

    @Query(value = "insert into menu (code,name,super_code) values (:code,:name,:superCode)",
            nativeQuery = true)
    @Modifying
    int add(@Param("code") String code,
            @Param("name") String name,
            @Param("superCode") String superCode);
}
