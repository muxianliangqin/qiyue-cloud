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

    public Map<String,Object> login(String username, String password) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("errorCode","0001");
        map.put("errorMsg","用户名或密码不正确");
        Optional<UserEntity> userEntity = userRepository.findByMobile(username);
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            password = BaseUtil.encrypt(password,user.getSalt());
            if (BaseUtil.slowEquals(password,user.getPassword())) {
                map = returnUser(user);
            }
        }
        return map;
    }

    public Map<String,Object> checkToken(String token) {
        Map<String,Object> map = new HashMap<>();
        map.put("errorCode","0001");
        map.put("errorMsg","token错误");
        Optional<UserEntity> userEntity = userRepository.findByToken(token);
        if (userEntity.isPresent()) {
            map = returnUser(userEntity.get());
        }
        return map;
    }

    public Map<String,Object> returnUser(UserEntity user) {
        Map<String,Object> map = new HashMap<>();
        map.put("errorCode","0000");
        map.put("errorMsg","success");
        Map<String,String> userMap = new HashMap<>();
        userMap.put("id",String.valueOf(user.getId()));
        userMap.put("username",user.getUsername());
        userMap.put("alias",user.getAlias());
        map.put("userInfo",userMap);
        return map;
    }
}
