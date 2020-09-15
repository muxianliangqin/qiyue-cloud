package com.qiyue.user.service;

import com.qiyue.user.entity.RoleEntity;
import com.qiyue.base.model.response.Response;
import com.qiyue.user.entity.RoleMenuEntity;
import com.qiyue.user.model.param.RoleMenusParam;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface RoleService {

    Response roleFindAll(Pageable pageable);

    Response<String> roleDel(Long roleId);

    Response<String> roleStop(Long roleId);

    Response<String> roleRestart(Long roleId);

    Response<String> roleAdd(RoleEntity roleEntity);

    Response<String> roleModify(RoleEntity RoleEntity);

    Response<List<RoleMenuEntity>> roleMenu(Long roleId);

    Response<List<RoleEntity>> findAll();

    /**
     * 查询用户拥有的角色
     *
     * @param userId
     * @return
     */
    Response<List<RoleEntity>> findByUserId(Long userId);

    /**
     * 设置角色的菜单权限
     *
     * @param param
     * @return
     */
    Response setRoleMenus(RoleMenusParam param);
}
