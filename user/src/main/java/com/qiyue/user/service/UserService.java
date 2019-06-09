package com.qiyue.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.qiyue.user.constant.Constant;
import com.qiyue.user.dao.entity.MenuEntity;
import com.qiyue.user.dao.entity.RightEntity;
import com.qiyue.user.dao.entity.RightEqualEntity;
import com.qiyue.user.dao.entity.UserEntity;
import com.qiyue.user.dao.repository.MenuRepository;
import com.qiyue.user.dao.repository.RightEqualRepository;
import com.qiyue.user.dao.repository.RightRepository;
import com.qiyue.user.dao.repository.UserRepository;
import com.qiyue.user.node.Menu;
import com.qiyue.user.node.Node;
import com.qiyue.user.node.NodeTree;
import com.qiyue.user.self.Error;
import com.qiyue.user.self.Response;
import com.qiyue.user.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RightRepository rightRepository;

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

    public Response login(String username, String password) throws Exception {
        Optional<UserEntity> userEntity = userRepository.findByMobile(username);
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            password = BaseUtil.encrypt(password,user.getSalt());
            if (BaseUtil.slowEquals(password,user.getPassword())) {
               user = userMessageFilter(user);
                return Response.success(user);
            }
        }
        return Response.fail("LOGIN_ERROR");
    }

    public Response checkToken(String token) {
        Optional<UserEntity> userEntity = userRepository.findByToken(token);
        if (userEntity.isPresent()) {
            UserEntity user = userMessageFilter(userEntity.get());
            return Response.success(user);
        }
        return Response.fail("TOKEN_ERROR");
    }

    public UserEntity userMessageFilter(UserEntity user) {
        user.setPassword(null);
        user.setSalt(null);
        return user;
    }

    public Response findUsers(Pageable pageable){
        Page<UserEntity> userEntityPage = userRepository.findAll(pageable);
        userEntityPage.getContent().forEach((k)->{k = userMessageFilter(k);});
        return Response.success(userEntityPage);
    }

    public Response findMenus(Pageable pageable){
        Page<MenuEntity> menuEntityPage = menuRepository.findAll(pageable);
        return Response.success(menuEntityPage);
    }

    public Response findRights(Pageable pageable){
        Page<RightEntity> rightEntityPage = rightRepository.findAll(pageable);
        return Response.success(rightEntityPage);
    }

}
