package com.qiyue.user.service.impl;

import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.BusinessException;
import com.qiyue.base.exceptions.DatabaseException;
import com.qiyue.base.model.response.Response;
import com.qiyue.base.node.Element;
import com.qiyue.base.node.TreeNode;
import com.qiyue.base.utils.EnumUtil;
import com.qiyue.base.utils.ParamVerify;
import com.qiyue.base.utils.StreamUtil;
import com.qiyue.base.utils.StringUtil;
import com.qiyue.user.constant.UserConstant;
import com.qiyue.user.dao.entity.MenuDao;
import com.qiyue.user.dao.entity.RoleDao;
import com.qiyue.user.dao.entity.RoleMenuDao;
import com.qiyue.user.dao.vo.MenuVODao;
import com.qiyue.user.entity.MenuEntity;
import com.qiyue.user.entity.RoleEntity;
import com.qiyue.user.entity.RoleMenuEntity;
import com.qiyue.user.enums.DataStateEnum;
import com.qiyue.user.enums.MenuOperateEnum;
import com.qiyue.user.enums.MenuPermissionEnum;
import com.qiyue.user.model.vo.MenuVO;
import com.qiyue.user.service.MenuService;
import com.qiyue.user.utils.IdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuImpl implements MenuService {

    private final RoleDao roleDao;
    private final RoleMenuDao roleMenuDao;
    private final MenuDao menuDao;
    private final MenuVODao menuVODao;

    public MenuImpl(RoleDao roleDao, RoleMenuDao roleMenuDao, MenuDao menuDao, MenuVODao menuVODao) {
        this.roleDao = roleDao;
        this.roleMenuDao = roleMenuDao;
        this.menuDao = menuDao;
        this.menuVODao = menuVODao;
    }

    /* 增 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Element<MenuVO>> menuAdd(MenuVO menuVO) {
        ParamVerify.isNotNull(menuVO.getName(), "菜单名称", "name");
        ParamVerify.isNotNull(menuVO.getSuperMenuId(), "菜单父节点id", "superMenuId");
        ParamVerify.validity(menuVO.getOperateCode(), MenuOperateEnum.values(), MenuOperateEnum::getCode, "operateCode");
        MenuVO.TRANSFER.accept(menuVO);
        MenuEntity menuEntity = new MenuEntity();
        BeanUtils.copyProperties(menuVO, menuEntity);
        menuEntity.setMenuId(IdUtil.nextId());
        menuDao.save(menuEntity);
        // 新增菜单默认赋权给管理员
        RoleEntity roleEntity = roleDao.findByCode(UserConstant.ROLE_ADMINISTRATOR).orElseThrow(() ->
                new BusinessException(ErrorEnum.RECORD_NOT_FOUND, "角色代码", UserConstant.ROLE_ADMINISTRATOR)
        );
        RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
        roleMenuEntity.setRoleId(roleEntity.getRoleId());
        roleMenuEntity.setMenuId(menuEntity.getMenuId());
        roleMenuDao.save(roleMenuEntity);
        MenuVO menuVO1 = MenuVO.CONVERTER.apply(menuEntity);
        Element<MenuVO> menuVOElement = Element.convertNotSuperLong(menuVO1, MenuVO::getMenuId);
        return Response.success(menuVOElement);
    }

    /* 删 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> menuDel(Long id) {
        menuDao.deleteById(id);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> menuDelBatch(List<Long> menuIds) {
        ParamVerify.isNotNull(menuIds, "需要删除的菜单menuIds集合", "menuIds");
        List<MenuEntity> menuEntities = menuIds.stream().map(k -> menuDao.findByMenuId(k).orElseThrow(() ->
                new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "menuId", k)
        )).collect(Collectors.toList());
        menuDao.deleteInBatch(menuEntities);
        return Response.success();
    }

    /* 改 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> menuStop(Long menuId) {
        MenuEntity menuEntity = menuDao.findByMenuId(menuId).orElseThrow(() ->
                new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "menuId", menuId)
        );
        menuEntity.setState(DataStateEnum.UNUSABLE.getState());
        menuDao.save(menuEntity);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> menuRestart(Long menuId) {
        MenuEntity menuEntity = menuDao.findByMenuId(menuId).orElseThrow(() ->
                new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "menuId", menuId)
        );
        menuEntity.setState(DataStateEnum.USABLE.getState());
        menuDao.save(menuEntity);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> menuStopBatch(List<Long> menuIds) {
        menuIds.forEach(k -> {
            MenuEntity menuEntity = menuDao.findByMenuId(k).orElseThrow(() ->
                    new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "menuId", k)
            );
            menuEntity.setState(DataStateEnum.UNUSABLE.getState());
            menuDao.save(menuEntity);
        });
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> menuRestartBatch(List<Long> menuIds) {
        menuIds.forEach(k -> {
            MenuEntity menuEntity = menuDao.findByMenuId(k).orElseThrow(() ->
                    new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "menuId", k)
            );
            menuEntity.setState(DataStateEnum.USABLE.getState());
            menuDao.save(menuEntity);
        });
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> menuModify(MenuVO menuVO) {
        ParamVerify.isNotNull(menuVO.getMenuId(), "菜单id", "menuId");
        ParamVerify.isNotNull(menuVO.getName(), "菜单名称", "name");
        ParamVerify.isNotNull(menuVO.getComponentName(), "组件名称", "componentName");
        ParamVerify.isNotNull(menuVO.getSuperMenuId(), "上级菜单id", "superMenuId");
        ParamVerify.validity(menuVO.getPermissionCode(), MenuPermissionEnum.values(), MenuPermissionEnum::getCode, "permissionCode");
        MenuEntity menuEntity = menuDao.findByMenuId(menuVO.getMenuId()).orElseThrow(() ->
                new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "菜单id", menuVO.getMenuId())
        );
        menuEntity.setMenuId(menuVO.getMenuId());
        menuEntity.setName(menuVO.getName());
        menuEntity.setDesc(menuVO.getDesc());
        menuEntity.setComponentName(menuVO.getComponentName());
        menuEntity.setSuperMenuId(menuVO.getSuperMenuId());
        menuDao.save(menuEntity);
        if (null != menuVO.getRoleId()) {
            RoleMenuEntity roleMenuEntity = roleMenuDao.findByRoleIdAndMenuId(menuVO.getRoleId(), menuVO.getMenuId()).orElseThrow(() ->
                    new DatabaseException(ErrorEnum.RECORD_NOT_FOUND,
                            "角色ID-菜单ID", StringUtil.format("{}-{}", menuVO.getRoleId(), menuVO.getMenuId()))
            );
            roleMenuEntity.setPermissionType(EnumUtil.convert(menuVO.getPermissionCode(), MenuPermissionEnum.values(),
                    MenuPermissionEnum::getCode, MenuPermissionEnum::getType));
            roleMenuDao.save(roleMenuEntity);
        }
        return Response.success();
    }

    /* 查 */
    @Override
    public Response<List<MenuVO>> getMenusByUserId(Long userId) {
        ParamVerify.isNotNull(userId, "用户ID", "userId");
        List<MenuVO> menuEntities = menuVODao.getMenusByUserId(userId);
        return Response.success(menuEntities);
    }

    @Override
    public Response<TreeNode> getMenuNodeByUserId(Long userId) {
        ParamVerify.isNotNull(userId, "用户ID", "userId");
        List<MenuVO> menuVOList = menuVODao.getMenusByUserId(userId);
        menuVOList = StreamUtil.peek(menuVOList, MenuVO.TRANSFER);
        menuVOList = MenuVO.DISTINCT.apply(menuVOList);
        return Response.success(TreeNode.convertLong(menuVOList, MenuVO::getMenuId, MenuVO::getSuperMenuId));
    }

    @Override
    public Response<List<MenuVO>> getMenusByRoleIds(List<Long> roleIds) {
        ParamVerify.isNotNull(roleIds, "角色ID集合", "roleIds");
        List<MenuVO> menuVOList = menuVODao.getMenusByRoleIds(roleIds);
        menuVOList = StreamUtil.peek(menuVOList, MenuVO.TRANSFER);
        menuVOList = MenuVO.DISTINCT.apply(menuVOList);
        return Response.success(menuVOList);
    }

    @Override
    public Response<List<MenuVO>> getMenusByRoleId(Long roleId) {
        ParamVerify.isNotNull(roleId, "角色ID", "roleId");
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        return getMenusByRoleIds(roleIds);
    }

    @Override
    public Response<TreeNode> getMenuNodeForAll() {
        List<Sort.Order> orderList = new ArrayList<>();
        orderList.add(new Sort.Order(Sort.Direction.ASC, "rankNo"));
        orderList.add(new Sort.Order(Sort.Direction.ASC, "sortNo"));
        Sort sort = Sort.by(orderList);
        List<MenuEntity> menuEntityList = menuDao.findAll(sort);
        List<MenuVO> menuVOList = StreamUtil.map(menuEntityList, MenuVO.CONVERTER);
        return Response.success(TreeNode.convertLong(menuVOList, MenuVO::getMenuId, MenuVO::getSuperMenuId));
    }

}
