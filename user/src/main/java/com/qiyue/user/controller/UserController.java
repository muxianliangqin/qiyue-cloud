package com.qiyue.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiyue.user.dao.entity.*;
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

import java.util.List;

@Slf4j
@RestController
@EnableDiscoveryClient
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/checkToken")
    public Response checkToken(String token) {
        return userService.checkToken(token);
    }

    /* 用户处理 */
    @RequestMapping("/user/findAllPage")
    public Response findAllPage(@PageableDefault(sort = "id",direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.findAllPage(pageable);
    }

    @RequestMapping("/user/findAll")
    public Response findAll() {
        return userService.findAll();
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

    @RequestMapping("/user/findUserMenus")
    public Response findUserMenus(int userId) {
        return userService.findUserMenus(userId);
    }

    @RequestMapping("/user/setUserMenus")
    public Response setUserMenus(@RequestParam("menus") String menus) {
        return userService.setUserMenus(menus);
    }
}
