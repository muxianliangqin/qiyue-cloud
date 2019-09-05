package com.qiyue.user.controller;

import com.qiyue.user.self.Response;
import com.qiyue.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@EnableDiscoveryClient
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Response login(HttpServletRequest request, String username, String password) throws Exception {
        return userService.login(request, username, password);
    }
}
