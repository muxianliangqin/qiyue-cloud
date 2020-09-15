package com.qiyue.user.model.param;

import com.qiyue.base.utils.EnumUtil;
import com.qiyue.user.entity.RoleMenuEntity;
import com.qiyue.user.enums.MenuPermissionEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Data
public class RoleMenusParam {
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 新增菜单
     */
    private List<MenuPermission> addMenus = new ArrayList<>();
    /**
     * 移除菜单
     */
    private List<MenuPermission> removeMenus = new ArrayList<>();

    @Data
    public static class MenuPermission {
        private Long menuId;
        private String permissionCode;
    }

    // 组装成RoleMenuEntity
    public static final BiFunction<RoleMenusParam, Function<RoleMenusParam, List<MenuPermission>>, List<RoleMenuEntity>> ASSEMBLE_ROLE_MENU =  (k, getMenuPermissionList) -> {
        List<RoleMenuEntity> roleMenuEntityList = new ArrayList<>();
        List<MenuPermission> menuPermissionList = getMenuPermissionList.apply(k);
        menuPermissionList.forEach(e -> {
            RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
            roleMenuEntity.setRoleId(k.getRoleId());
            roleMenuEntity.setMenuId(e.getMenuId());
            roleMenuEntity.setPermissionType(EnumUtil.convert(e.getPermissionCode(), MenuPermissionEnum.values(), MenuPermissionEnum::getCode, MenuPermissionEnum::getType));
            roleMenuEntityList.add(roleMenuEntity);
        });
        return roleMenuEntityList;
    };

}
