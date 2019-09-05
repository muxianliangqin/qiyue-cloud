package com.qiyue.user.controller;

import com.qiyue.user.dao.entity.RightEntity;
import com.qiyue.user.self.Response;
import com.qiyue.user.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
public class RightController {

    @Autowired
    private RightService rightService;

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
