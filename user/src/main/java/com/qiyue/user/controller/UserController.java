package com.qiyue.user.controller;

import com.qiyue.user.node.Node;
import com.qiyue.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@EnableDiscoveryClient
public class UserController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "services:" + discoveryClient.getInstances("qiyue-cloud-crawler");
    }

    @GetMapping("/getMenuNode")
    public String getMenuNode(int id){
        return userService.getMenuNode(id);
    }

}
