package com.qiyue.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.qiyue.user.constant.Constant;
import com.qiyue.user.dao.entity.MenuEntity;
import com.qiyue.user.dao.entity.UserEntity;
import com.qiyue.user.dao.repository.MenuRepository;
import com.qiyue.user.dao.repository.UserRepository;
import com.qiyue.user.node.Menu;
import com.qiyue.user.node.Node;
import com.qiyue.user.node.NodeTree;
import com.qiyue.user.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Map<String,String> login(String username, String password) throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("errorCode","0001");
        map.put("errorMsg","用户名或密码不正确");
        Optional<UserEntity> userEntity = userRepository.findByMobile(username);
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            password = BaseUtil.encrypt(password,user.getSalt());
            if (BaseUtil.slowEquals(password,user.getPassword())) {
                map.put("errorCode","0000");
                map.put("errorMsg","success");
                map.put("id",String.valueOf(user.getId()));
                map.put("username",user.getUsername());
                map.put("alias",user.getAlias());
            }
        }
        return map;
    }
}
