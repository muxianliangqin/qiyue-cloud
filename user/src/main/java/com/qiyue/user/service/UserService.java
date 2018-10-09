package com.qiyue.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiyue.user.dao.entity.MenuEntity;
import com.qiyue.user.dao.repository.MenuRepository;
import com.qiyue.user.node.Menu;
import com.qiyue.user.node.Node;
import com.qiyue.user.node.NodeTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private MenuRepository menuRepository;

    public String getMenuNode(int id){
        List<MenuEntity> menuEntities = menuRepository.getMenus(id);
        Node node = NodeTree.getInstance();
        menuEntities.forEach(menuEntity->{
            Menu menu = new Menu();
            menu.setCode(menuEntity.getCode());
            menu.setName(menuEntity.getName());
            menu.setUrl(menuEntity.getUrl());
            menu.setSupCode(menuEntity.getSuperCode());
            menu.setDesc(menuEntity.getDesc());
            NodeTree.insert(node,menu);
        });
        JSONObject json = JSON.parseObject(node.toString());
        return json.toString();
    }
}
