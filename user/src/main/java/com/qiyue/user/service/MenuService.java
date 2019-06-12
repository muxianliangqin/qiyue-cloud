package com.qiyue.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiyue.user.constant.Constant;
import com.qiyue.user.dao.entity.MenuEntity;
import com.qiyue.user.dao.entity.RightEqualEntity;
import com.qiyue.user.dao.repository.MenuRepository;
import com.qiyue.user.dao.repository.RightEqualRepository;
import com.qiyue.user.node.Menu;
import com.qiyue.user.node.Node;
import com.qiyue.user.node.NodeTree;
import com.qiyue.user.self.Response;
import com.qiyue.user.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RightEqualRepository rightEqualRepository;

    public Response getMenuNode(int userId){
        List<MenuEntity> menuEntities = menuRepository.getMenus(userId);
        List<RightEqualEntity> rightEqualEntities = rightEqualRepository.findByUserIdAndState(userId, "0");
        Map<String,List<Integer>> rightEqual = new HashMap<>();
        rightEqualEntities.forEach((v)->{
            if (!rightEqual.containsKey(v.getMenuCode())) {
                List<Integer> rights = new ArrayList<>();
                rights.add(v.getEqualUserId());
                rightEqual.put(v.getMenuCode(),rights);
            } else {
                rightEqual.get(v.getMenuCode()).add(v.getEqualUserId());
            }
        });
        Node node = NodeTree.getInstance();
        menuEntities.forEach(menuEntity->{
            Menu menu = new Menu();
            menu.setId(menuEntity.getCode());
            menu.setName(menuEntity.getName());
            menu.setUrl(menuEntity.getUrl());
            menu.setSupId(menuEntity.getSuperCode());
            menu.setDesc(menuEntity.getDesc());
            menu.setXpath(menuEntity.getXpath());
            menu.setRightEqual(rightEqual.get(menuEntity.getCode()));
            NodeTree.insert(node,menu);
        });
        JSONObject json = JSON.parseObject(node.toString());
        return Response.success(json);
    }

    public Response menuFindAll(Pageable pageable){
        Page<MenuEntity> menuEntityPage = menuRepository.findAll(pageable);
        return Response.success(menuEntityPage);
    }

    @Transactional
    public Response menuDel(int id){
        menuRepository.deleteById(id);
        return Response.success("ok");
    }

    @Transactional
    public Response menuStop(int id){
        int num = menuRepository.stop(id);
        return Response.success(num);
    }

    @Transactional
    public Response menuRestart(int id){
        int num = menuRepository.restart(id);
        return Response.success(num);
    }

    @Transactional
    public Response menuAdd(MenuEntity menuEntity){
        int num = menuRepository.add(menuEntity.getCode(), menuEntity.getName(), menuEntity.getSuperCode());
        return Response.success(num);
    }

    @Transactional
    public Response menuModify(MenuEntity menuEntity){
        Optional<MenuEntity> menuEntityOptional = menuRepository.findById(menuEntity.getId());
        if (!menuEntityOptional.isPresent()) {
            Response.fail("NO_RECORD");
        }
        MenuEntity oldOne = menuEntityOptional.get();
        oldOne.setCode(menuEntity.getCode());
        oldOne.setName(menuEntity.getName());
        oldOne.setSuperCode(menuEntity.getSuperCode());
        oldOne.setUpdateTime(DateUtil.getSystemTime(Constant.DATE_FORMATER_WITH_HYPHEN));
        MenuEntity newOne = menuRepository.saveAndFlush(oldOne);
        return Response.success(newOne);
    }

}
