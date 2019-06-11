package com.qiyue.user.controller;

import com.qiyue.user.dao.entity.MenuEntity;
import com.qiyue.user.dao.entity.RightEntity;
import com.qiyue.user.dao.entity.UserEntity;
import com.qiyue.user.self.Response;
import com.qiyue.user.service.MenuService;
import com.qiyue.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@EnableDiscoveryClient
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/getMenuNode")
    public Response getMenuNode(int userId){
        return menuService.getMenuNode(userId);
    }

    @RequestMapping("/login")
    public Response login(String username, String password) throws Exception {
        return userService.login(username,password);
    }

    @RequestMapping("/checkToken")
    public Response checkToken(String token) {
        return userService.checkToken(token);
    }

    @RequestMapping("/user/findAll")
    public Response userFindAll(@PageableDefault(sort = "id",direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.userFindAll(pageable);
    }


    @RequestMapping("/user/del")
    public Response userDel(int userId) {
        return userService.userDel(userId);
    }

    @RequestMapping("/user/stop")
    public Response userStop(int userId) {
        return userService.userStop(userId);
    }

    @RequestMapping("/user/restart")
    public Response userRestart(int userId) {
        return userService.userRestart(userId);
    }

    @RequestMapping("/user/add")
    public Response userAdd(UserEntity userEntity) throws Exception {
        return userService.userAdd(userEntity);
    }

    @RequestMapping("/user/modify")
    public Response userModify(UserEntity userEntity) {
        return userService.userModify(userEntity);
    }

    @RequestMapping("/menu/findAll")
    public Response menuFindAll(@PageableDefault(sort = "code",direction = Sort.Direction.ASC) Pageable pageable) {
        return menuService.menuFindAll(pageable);
    }

    @RequestMapping("/menu/del")
    public Response menuDel(int menuId) {
        return menuService.menuDel(menuId);
    }

    @RequestMapping("/menu/stop")
    public Response menuStop(int menuId) {
        return menuService.menuStop(menuId);
    }

    @RequestMapping("/menu/restart")
    public Response menuRestart(int menuId) {
        return menuService.menuRestart(menuId);
    }

    @RequestMapping("/menu/add")
    public Response menuAdd(MenuEntity menuEntity) {
        return menuService.menuAdd(menuEntity);
    }

    @RequestMapping("/menu/modify")
    public Response menuModify(MenuEntity menuEntity) {
        return menuService.menuModify(menuEntity);
    }

    @RequestMapping("/findRights")
    public Response findRights(@PageableDefault(sort = "code",direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.findRights(pageable);
    }

}
