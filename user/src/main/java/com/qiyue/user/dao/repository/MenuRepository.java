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
    @Query(value = "select m.id,m.code,m.name,m.super_code,m.url,m.menu_desc, \n" +
            "m.menu_state,m.create_time,m.create_user,m.update_time,m.update_user \n" +
            "   from menu m \n" +
            "   join user_menu um on um.menu_code=m.code and um.state='0' \n" +
            "   join user u on u.id=um.user_id and u.user_state ='0' \n" +
            "   where menu_state='0'" +
            "   and u.id = ?1", nativeQuery = true)
    List<MenuEntity> getMenus(int userId);

    Page<MenuEntity> findAll(Pageable pageable);

    @Query(value = "update menu set menu_state = '1' where id in :ids",
            nativeQuery = true)
    @Modifying
    int stop(@Param("ids") List<Integer> ids);

    @Query(value = "update menu set menu_state = '0' where id in :ids",
            nativeQuery = true)
    @Modifying
    int restart(@Param("ids") List<Integer> ids);

    @Query(value = "insert into menu (code,name,url,super_code)" +
            " values (:code,:name,:url,:superCode)",
            nativeQuery = true)
    @Modifying
    int add(@Param("code") String code,
            @Param("name") String name,
            @Param("url") String url,
            @Param("superCode") String superCode);
}
