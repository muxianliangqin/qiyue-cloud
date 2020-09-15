package com.qiyue.user.controller;

import com.qiyue.base.model.request.Request;
import com.qiyue.user.model.vo.UserInfoVO;
import com.qiyue.user.entity.*;
import com.qiyue.base.model.response.Response;
import com.qiyue.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@EnableDiscoveryClient
public class UserController {

    @Autowired
    private UserService userService;

    /* 增 */
    @RequestMapping("/user/add")
    public Response userAdd(@RequestBody Request<UserEntity> request) throws Exception {
        return userService.userAdd(request);
    }

    /* 删 */
    @RequestMapping("/user/del")
    public Response userDel(@RequestBody Request<UserEntity> request) {
        return userService.userDel(request);
    }

    /* 改 */
    @RequestMapping("/user/stop")
    public Response userStop(@RequestBody Request<UserEntity> request) {
        return userService.userStop(request);
    }

    @RequestMapping("/user/restart")
    public Response userRestart(@RequestBody Request<UserEntity> request) {
        return userService.userRestart(request);
    }

    @RequestMapping("/user/modify")
    public Response userModify(@RequestBody Request<UserEntity> request) {
        return userService.userModify(request);
    }

    /* 查 */
    @RequestMapping("/user/findAllPage")
    public Response<Page<UserInfoVO>> findAllPage(@RequestBody Request request) {
        return userService.findAllPage(request.getPage());
    }

    @RequestMapping("/user/findAll")
    public Response<List<UserInfoVO>> findAll() {
        return userService.findAll();
    }

    @RequestMapping("/user/findByUsernameLike")
    public Response<List<UserInfoVO>> findByUsernameLike(@RequestBody Request<String> request) {
        return userService.findByUsernameLike(request);
    }

    @RequestMapping("/user/findUserMenus")
    public Response findUserMenus(@RequestBody Request<UserEntity> request) {
        return userService.findUserMenus(request);
    }

}
