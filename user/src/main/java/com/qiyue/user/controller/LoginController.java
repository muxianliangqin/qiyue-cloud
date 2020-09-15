package com.qiyue.user.controller;

import com.qiyue.base.model.request.Request;
import com.qiyue.base.model.response.Response;
import com.qiyue.user.entity.UserEntity;
import com.qiyue.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * springboot获取参数注解
 * {@PathVariable}
 * 请求类型：GET、所有 'Content-Type'
 * {@RequestParam}
 * 请求类型：GET，所有 'Content-Type'
 * 请求类型：POST/PUT/DELETE/PATCH，'Content-Type': form-data、'application/x-www-form-urlencoded
 * {@RequestBody}
 * 请求类型：POST/PUT/DELETE/PATCH，'Content-Type': 'application/json'
 */
@RestController
@EnableDiscoveryClient
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public Response login(@RequestBody Request<UserEntity> request,
                          HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse) throws Exception {
        return loginService.login(request, httpServletRequest, httpServletResponse);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Response logout(HttpServletRequest request, HttpServletResponse response) {
        return loginService.logout(request, response);
    }

}
