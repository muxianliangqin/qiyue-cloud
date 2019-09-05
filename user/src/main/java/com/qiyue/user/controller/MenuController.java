package com.qiyue.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiyue.user.dao.entity.MenuEntity;
import com.qiyue.user.dao.entity.MenuLoanEntity;
import com.qiyue.user.self.Response;
import com.qiyue.user.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@EnableDiscoveryClient
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/getMenuNode")
    public Response getMenuNode(int userId){
        return menuService.getMenuNode(userId);
    }

    /* 菜单处理 */
    @RequestMapping("/menu/findAllPage")
    public Response findAll(@PageableDefault(sort = "code",direction = Sort.Direction.ASC) Pageable pageable) {
        return menuService.findAll(pageable);
    }

    @RequestMapping("/menu/findAll")
    public Response menuFindAll() {
        return menuService.menuFindAll();
    }

    @RequestMapping("/menu/del")
    public Response menuDel(int id) {
        return menuService.menuDel(id);
    }

    @RequestMapping("/menu/stop")
    public Response menuStop(@RequestParam(name = "id") int id) {
        return menuService.menuStop(id);
    }

    @RequestMapping("/menu/restart")
    public Response menuRestart(@RequestParam(name = "id") int id) {
        return menuService.menuRestart(id);
    }

    @RequestMapping("/menu/delBatch")
    public Response menuDelBatch(@RequestParam(name = "ids") List<Integer> ids) {
        return menuService.menuDelBatch(ids);
    }

    @RequestMapping("/menu/stopBatch")
    public Response menuStopBatch(@RequestParam(name = "ids") List<Integer> ids) {
        return menuService.menuStopBatch(ids);
    }

    @RequestMapping("/menu/restartBatch")
    public Response menuRestartBatch(@RequestParam(name = "ids") List<Integer> ids) {
        return menuService.menuRestartBatch(ids);
    }

    @RequestMapping("/menu/add")
    public Response menuAdd(MenuEntity menuEntity) {
        return menuService.menuAdd(menuEntity);
    }

    @RequestMapping("/menu/modify")
    public Response menuModify(MenuEntity menuEntity) {
        return menuService.menuModify(menuEntity);
    }

    @RequestMapping("/menuLoan/addBatch")
    public Response menuLoanAddBatch(@RequestParam(name = "menuLoanEntities") String json) {
        List<MenuLoanEntity> menuLoanEntities = JSONObject.parseArray(json, MenuLoanEntity.class);
        return menuService.menuLoanAddBatch(menuLoanEntities);
    }

}
