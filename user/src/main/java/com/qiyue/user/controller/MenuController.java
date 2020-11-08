package com.qiyue.user.controller;

import com.qiyue.base.node.Element;
import com.qiyue.base.node.TreeNode;
import com.qiyue.base.model.request.Request;
import com.qiyue.base.model.response.Response;
import com.qiyue.user.model.vo.MenuVO;
import com.qiyue.user.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableDiscoveryClient
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /* 增 */
    @RequestMapping("/add")
    public Response<Element<MenuVO>> menuAdd(@RequestBody Request<MenuVO> request) {
        return menuService.menuAdd(request.getParams());
    }

    /* 删 */
    @RequestMapping("/del")
    public Response<String> menuDel(@RequestBody Request<Long> request) {
        return menuService.menuDel(request.getParams());
    }


    @RequestMapping("/delBatch")
    public Response<String> menuDelBatch(@RequestBody Request<List<Long>> request) {
        return menuService.menuDelBatch(request.getParams());
    }

    /* 改 */
    @RequestMapping("/stop")
    public Response<String> menuStop(@RequestBody Request<Long> request) {
        return menuService.menuStop(request.getParams());
    }

    @RequestMapping("/restart")
    public Response<String> menuRestart(@RequestBody Request<Long> request) {
        return menuService.menuRestart(request.getParams());
    }

    @RequestMapping("/stopBatch")
    public Response<String> menuStopBatch(@RequestBody Request<List<Long>> request) {
        return menuService.menuStopBatch(request.getParams());
    }

    @RequestMapping("/restartBatch")
    public Response<String> menuRestartBatch(@RequestBody Request<List<Long>> request) {
        return menuService.menuRestartBatch(request.getParams());
    }

    @RequestMapping("/modify")
    public Response<String> menuModify(@RequestBody Request<MenuVO> request) {
        return menuService.menuModify(request.getParams());
    }

    /* 查 */
    @RequestMapping("/getMenusByUserId")
    public Response<List<MenuVO>> getMenusByUserId(@RequestBody Request<Long> request) {
        return menuService.getMenusByUserId(request.getParams());
    }

    @RequestMapping("/getMenusByRoleIds")
    public Response<List<MenuVO>> getMenusByRoleIds(@RequestBody Request<List<Long>> request) {
        return menuService.getMenusByRoleIds(request.getParams());
    }

    @RequestMapping("/getMenusByRoleId")
    public Response<List<MenuVO>> getMenusByRoleId(@RequestBody Request<Long> request) {
        return menuService.getMenusByRoleId(request.getParams());
    }

    @RequestMapping("/getMenuNodeByUserId")
    public Response<TreeNode> getMenuNodeByUserId(@RequestBody Request<Long> request) {
        return menuService.getMenuNodeByUserId(request.getParams());
    }

    @RequestMapping("/getMenuNodeForAll")
    public Response<TreeNode> getMenuNodeForAll() {
        return menuService.getMenuNodeForAll();
    }

}
