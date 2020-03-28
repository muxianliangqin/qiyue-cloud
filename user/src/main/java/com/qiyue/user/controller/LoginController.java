package com.qiyue.user.controller;

import com.qiyue.service.response.Response;
import com.qiyue.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@EnableDiscoveryClient
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Response login(HttpServletRequest request,
                          HttpServletResponse response,
                          String username, String password) throws Exception {
        return userService.login(request, response, username, password);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Response logout(HttpServletRequest request, HttpServletResponse response) {
        return userService.logout(request, response);
    }
}
