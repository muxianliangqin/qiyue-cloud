package com.qiyue.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiyue.user.dao.entity.MenuEntity;
import com.qiyue.user.dao.entity.UserEntity;
import com.qiyue.user.dao.repository.MenuRepository;
import com.qiyue.user.dao.repository.UserRepository;
import com.qiyue.user.node.Menu;
import com.qiyue.user.node.Node;
import com.qiyue.user.node.NodeTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    public String getMenuNode(int userId){
        List<MenuEntity> menuEntities = menuRepository.getMenus(userId);
        Node node = NodeTree.getInstance();
        menuEntities.forEach(menuEntity->{
            Menu menu = new Menu();
            menu.setId(menuEntity.getCode());
            menu.setName(menuEntity.getName());
            menu.setUrl(menuEntity.getUrl());
            menu.setSupId(menuEntity.getSuperCode());
            menu.setDesc(menuEntity.getDesc());
            menu.setXpath(menuEntity.getXpath());
            NodeTree.insert(node,menu);
        });
        JSONObject json = JSON.parseObject(node.toString());
        return json.toString();
    }

    public Optional<UserEntity> login(String mobile, String password) {
        return userRepository.findByMobileAndPassword(mobile,password);
    }
}
