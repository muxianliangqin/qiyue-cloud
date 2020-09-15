package com.qiyue.user.controller;

import com.qiyue.base.model.request.Request;
import com.qiyue.user.entity.RoleEntity;
import com.qiyue.base.model.response.Response;
import com.qiyue.user.model.param.RoleMenusParam;
import com.qiyue.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @RequestMapping("/add")
    public Response rightAdd(RoleEntity roleEntity) {
        return roleService.roleAdd(roleEntity);
    }

    @RequestMapping("/del")
    public Response rightDel(Request<Long> request) {
        return roleService.roleDel(request.getParams());
    }

    @RequestMapping("/stop")
    public Response rightStop(Request<Long> request) {
        return roleService.roleStop(request.getParams());
    }

    @RequestMapping("/restart")
    public Response rightRestart(Request<Long> request) {
        return roleService.roleRestart(request.getParams());
    }

    @RequestMapping("/modify")
    public Response rightModify(RoleEntity roleEntity) {
        return roleService.roleModify(roleEntity);
    }

    @RequestMapping("/setRoleMenus")
    public Response setRoleMenus(@RequestBody Request<RoleMenusParam> request) {
        return roleService.setRoleMenus(request.getParams());
    }

    /* 查 */
    @RequestMapping("/menu")
    public Response rightMenu(Long roleId) {
        return roleService.roleMenu(roleId);
    }

    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    @RequestMapping("/findAllByPage")
    public Response rightFindAll(@PageableDefault(sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return roleService.roleFindAll(pageable);
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @RequestMapping("/findAll")
    public Response findAll() {
        return roleService.findAll();
    }

    /**
     * 查询用户拥有的角色
     *
     * @return
     */
    @RequestMapping("/findByUserId")
    public Response findByUserId(@RequestBody Request<Long> request) {
        return roleService.findByUserId(request.getParams());
    }

}
