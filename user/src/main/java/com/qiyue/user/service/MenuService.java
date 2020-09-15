package com.qiyue.user.service;

import com.qiyue.base.node.Element;
import com.qiyue.base.node.TreeNode;
import com.qiyue.base.model.response.Response;
import com.qiyue.user.model.vo.MenuVO;

import java.util.List;

public interface MenuService {


    Response<Element<MenuVO>> menuAdd(MenuVO menuVO);

    Response menuDel(Long menuId);

    Response menuDelBatch(List<Long> menuIds);

    Response menuStop(Long menuId);

    Response menuRestart(Long menuId);

    Response menuStopBatch(List<Long> menuIds);

    Response menuRestartBatch(List<Long> menuIds);

    Response<String> menuModify(MenuVO menuVO);

    /**
     * 根据userId查询拥有的菜单权限
     *
     * @param userId
     * @return
     */
    Response<List<MenuVO>> getMenusByUserId(Long userId);

    Response<List<MenuVO>> getMenusByRoleIds(List<Long> roleIds);

    Response<List<MenuVO>> getMenusByRoleId(Long roleId);

    Response<TreeNode> getMenuNodeByUserId(Long userId);

    Response<TreeNode> getMenuNodeForAll();
}
