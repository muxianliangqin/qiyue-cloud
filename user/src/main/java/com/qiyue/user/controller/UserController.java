package com.qiyue.user.controller;

import com.qiyue.user.dao.entity.MenuEntity;
import com.qiyue.user.dao.entity.RightEntity;
import com.qiyue.user.dao.entity.UserEntity;
import com.qiyue.user.self.Response;
import com.qiyue.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@EnableDiscoveryClient
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getMenuNode")
    public Response getMenuNode(int userId){
        return userService.getMenuNode(userId);
    }

    @RequestMapping("/login")
    public Response login(String username, String password) throws Exception {
        return userService.login(username,password);
    }

    @RequestMapping("/checkToken")
    public Response checkToken(String token) {
        return userService.checkToken(token);
    }

    @RequestMapping("/findUsers")
    public Response findUsers(@PageableDefault(sort = "updateTime",direction = Sort.Direction.DESC) Pageable pageable) {
        return userService.findUsers(pageable);
    }
    @RequestMapping("/findMenus")
    public Response findMenus(@PageableDefault(sort = "updateTime",direction = Sort.Direction.DESC) Pageable pageable) {
        return userService.findMenus(pageable);
    }
    @RequestMapping("/findRights")
    public Response findRights(@PageableDefault(sort = "updateTime",direction = Sort.Direction.DESC) Pageable pageable) {
        return userService.findRights(pageable);
    }

}
