package com.qiyue.user.model.vo;

import com.qiyue.base.utils.EnumUtil;
import com.qiyue.base.utils.ParamVerify;
import com.qiyue.user.entity.MenuEntity;
import com.qiyue.user.enums.DataStateEnum;
import com.qiyue.user.enums.MenuOperateEnum;
import com.qiyue.user.enums.MenuPermissionEnum;
import com.qiyue.user.enums.MenuTypeEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Entity
public class MenuVO implements Serializable {

    @Id
    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String desc;

    @Column(name = "component_name")
    private String componentName;

    @Column(name = "super_menu_id")
    private Long superMenuId;

    /**
     * 类型：
     * 1-模块
     * 2-菜单目录。
     * 3-菜单。
     * 4-页面，非菜单直接打开的页面
     * 5-按钮
     * 6-链接
     */
    @Column(name = "type")
    private Integer type;
    @Transient
    private String typeCode;
    @Transient
    private String typeName;

    @Column(name = "role_id")
    private Long roleId;

    /**
     * 执行后台操作的类型，1-只读，2-新增,3-更新，4-删除
     */
    @Column(name = "operate_type")
    private Integer operateType;
    @Transient
    private String operateCode;
    @Transient
    private String operateName;

    /**
     * 前端页面，该角色对组件的权限，可以有多个，以英文逗号【,】分隔。
     * 0-展示，
     * 1-禁用
     */
    @Column(name = "permission_type")
    private Integer permissionType;
    @Transient
    private String permissionCode;
    @Transient
    private String permissionName;

    /**
     * 菜单图标
     */
    @Column(name = "icon")
    private String icon;

    @Column(name = "state")
    private Integer state;
    @Transient
    private String stateName;

    @Column(name = "rank_no")
    private Integer rankNo;

    @Column(name = "sort_no")
    private Integer sortNo;

    /**
     * 代码转义
     */
    public static final Consumer<MenuVO> TRANSFER = (vo) -> {
        // 映射操作类型
        if (null != vo.getOperateType()) {
            vo.setOperateCode(EnumUtil.convert(vo.getOperateType(), MenuOperateEnum.values(),
                    MenuOperateEnum::getType, MenuOperateEnum::getCode));
            vo.setOperateName(EnumUtil.convert(vo.getOperateType(), MenuOperateEnum.values(),
                    MenuOperateEnum::getType, MenuOperateEnum::getName));
        } else if (StringUtils.isNotEmpty(vo.getOperateCode())) {
            vo.setOperateType(EnumUtil.convert(vo.getOperateCode(), MenuOperateEnum.values(),
                    MenuOperateEnum::getCode, MenuOperateEnum::getType));
            vo.setOperateName(EnumUtil.convert(vo.getOperateCode(), MenuOperateEnum.values(),
                    MenuOperateEnum::getCode, MenuOperateEnum::getName));
        }
        // 映射菜单类型
        if (null != vo.getType()) {
            vo.setTypeCode(EnumUtil.convert(vo.getType(), MenuTypeEnum.values(),
                    MenuTypeEnum::getType, MenuTypeEnum::getCode));
            vo.setTypeName(EnumUtil.convert(vo.getType(), MenuTypeEnum.values(),
                    MenuTypeEnum::getType, MenuTypeEnum::getName));
        } else if (StringUtils.isNotEmpty(vo.getTypeCode())) {
            vo.setType(EnumUtil.convert(vo.getTypeCode(), MenuTypeEnum.values(),
                    MenuTypeEnum::getCode, MenuTypeEnum::getType));
            vo.setTypeName(EnumUtil.convert(vo.getTypeCode(), MenuTypeEnum.values(),
                    MenuTypeEnum::getCode, MenuTypeEnum::getName));
        }
        // 映射权限类型
        if (null != vo.getPermissionType()) {
            vo.setPermissionCode(EnumUtil.convert(vo.getPermissionType(), MenuPermissionEnum.values(),
                    MenuPermissionEnum::getType, MenuPermissionEnum::getCode));
            vo.setPermissionName(EnumUtil.convert(vo.getPermissionType(), MenuPermissionEnum.values(),
                    MenuPermissionEnum::getType, MenuPermissionEnum::getName));
        } else if (StringUtils.isNotEmpty(vo.getPermissionCode())) {
            vo.setPermissionType(EnumUtil.convert(vo.getPermissionCode(), MenuPermissionEnum.values(),
                    MenuPermissionEnum::getCode, MenuPermissionEnum::getType));
            vo.setPermissionName(EnumUtil.convert(vo.getPermissionCode(), MenuPermissionEnum.values(),
                    MenuPermissionEnum::getCode, MenuPermissionEnum::getName));
        }
        vo.setStateName(EnumUtil.convert(vo.getState(), DataStateEnum.values(),
                DataStateEnum::getState, DataStateEnum::getMsg));
    };

    /**
     * 将数据库实体类装换为展示层对象
     */
    public static final Function<MenuEntity, MenuVO> CONVERTER = (entity) -> {
        MenuVO menuVO = new MenuVO();
        BeanUtils.copyProperties(entity, menuVO);
        menuVO.setPermissionType(MenuPermissionEnum.SHOW.getType());
        MenuVO.TRANSFER.accept(menuVO);
        return menuVO;
    };

    /**
     * 去重
     */
    public static final Function<List<MenuVO>, List<MenuVO>> DISTINCT = (menuVOList) -> {
        ParamVerify.isNotNull(menuVOList, "待去重的menuVO集合", "menuVOList");
        /*
         * 1、先根据 rankNo、sortNo、menuId排序去重
         */
        return menuVOList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(MenuVO::getRankNo)
                        .thenComparing(MenuVO::getSortNo)
                        .thenComparing(MenuVO::getMenuId))), ArrayList::new));
    };
}
