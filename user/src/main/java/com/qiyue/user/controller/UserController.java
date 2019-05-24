package com.qiyue.user.controller;

import com.qiyue.user.dao.entity.UserEntity;
import com.qiyue.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
    public String getMenuNode(int userId){
        return userService.getMenuNode(userId);
    }

    @RequestMapping("/login")
    public Map<String,Object> login(String username, String password) throws Exception {
        return userService.login(username,password);
    }

    @RequestMapping("/checkToken")
    public Map<String,Object> checkToken(String token) {
        return userService.checkToken(token);
    }

}
