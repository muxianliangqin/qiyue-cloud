package com.qiyue.user.controller;

import com.qiyue.user.dao.entity.MenuEntity;
import com.qiyue.user.dao.entity.RightEntity;
import com.qiyue.user.dao.entity.UserEntity;
import com.qiyue.user.self.Response;
import com.qiyue.user.service.MenuService;
import com.qiyue.user.service.RightService;
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

    @Autowired
    private RightService rightService;

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

    /* 用户处理 */
    @RequestMapping("/user/findAll")
    public Response userFindAll(@PageableDefault(sort = "id",direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.userFindAll(pageable);
    }

    @RequestMapping("/user/del")
    public Response userDel(int id) {
        return userService.userDel(id);
    }

    @RequestMapping("/user/stop")
    public Response userStop(int id) {
        return userService.userStop(id);
    }

    @RequestMapping("/user/restart")
    public Response userRestart(int id) {
        return userService.userRestart(id);
    }

    @RequestMapping("/user/add")
    public Response userAdd(UserEntity userEntity) throws Exception {
        return userService.userAdd(userEntity);
    }

    @RequestMapping("/user/modify")
    public Response userModify(UserEntity userEntity) {
        return userService.userModify(userEntity);
    }

    /* 菜单处理 */
    @RequestMapping("/menu/findAll")
    public Response menuFindAll(@PageableDefault(sort = "code",direction = Sort.Direction.ASC) Pageable pageable) {
        return menuService.menuFindAll(pageable);
    }

    @RequestMapping("/menu/del")
    public Response menuDel(int id) {
        return menuService.menuDel(id);
    }

    @RequestMapping("/menu/stop")
    public Response menuStop(int id) {
        return menuService.menuStop(id);
    }

    @RequestMapping("/menu/restart")
    public Response menuRestart(int id) {
        return menuService.menuRestart(id);
    }

    @RequestMapping("/menu/add")
    public Response menuAdd(MenuEntity menuEntity) {
        return menuService.menuAdd(menuEntity);
    }

    @RequestMapping("/menu/modify")
    public Response menuModify(MenuEntity menuEntity) {
        return menuService.menuModify(menuEntity);
    }

    /* 权限处理 */
    @RequestMapping("/right/findAll")
    public Response rightFindAll(@PageableDefault(sort = "code",direction = Sort.Direction.ASC) Pageable pageable) {
        return rightService.rightFindAll(pageable);
    }

    @RequestMapping("/right/del")
    public Response rightDel(int id) {
        return rightService.rightDel(id);
    }

    @RequestMapping("/right/stop")
    public Response rightStop(int id) {
        return rightService.rightStop(id);
    }

    @RequestMapping("/right/restart")
    public Response rightRestart(int id) {
        return rightService.rightRestart(id);
    }

    @RequestMapping("/right/add")
    public Response rightAdd(RightEntity rightEntity) {
        return rightService.rightAdd(rightEntity);
    }

    @RequestMapping("/right/modify")
    public Response rightModify(RightEntity rightEntity) {
        return rightService.rightModify(rightEntity);
    }

    @RequestMapping("/right/menu")
    public Response rightMenu(String rightCode) {
        return rightService.rightMenu(rightCode);
    }

}
