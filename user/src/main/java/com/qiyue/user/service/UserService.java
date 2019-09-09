package com.qiyue.user.service;

import com.alibaba.fastjson.JSONObject;
import com.qiyue.common.session.User;
import com.qiyue.common.constant.Constant;
import com.qiyue.user.dao.em.UserEntityManager;
import com.qiyue.user.dao.entity.UserEntity;
import com.qiyue.user.dao.entity.UserMenuEntity;
import com.qiyue.user.dao.repository.UserMenuRepository;
import com.qiyue.user.dao.repository.UserRepository;
import com.qiyue.common.response.Response;
import com.qiyue.common.util.BaseUtil;
import com.qiyue.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMenuRepository userMenuRepository;

    @Autowired
    private UserEntityManager userEntityManager;

    public Response login(HttpServletRequest request,
                          String username, String password) throws Exception {
        Optional<UserEntity> userEntity = userRepository.findByMobile(username);
        if (!userEntity.isPresent()) {
            return Response.fail("LOGIN_ERROR");
        }
        UserEntity user = userEntity.get();
        password = BaseUtil.encrypt(password,user.getSalt());
        if (!BaseUtil.slowEquals(password, user.getPassword())) {
            return Response.fail("LOGIN_ERROR");
        }
        user = userMessageFilter(user);
        HttpSession session = request.getSession();
        User sessionUser = (User)session.getAttribute(Constant.SESSION_USER);
        // 如果session中已存在的user信息与登录用户不一致
        if (null != sessionUser && !user.getUsername().equals(sessionUser.getUsername())) {
            return Response.fail("LOGIN_MULTI_ERROR");
        }
        session.setAttribute(Constant.SESSION_USER, transformUser(user));
        return Response.success(user);
    }

    public Response logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (null != session) {
            session.invalidate();
        }
        return Response.success();
    }

    public User transformUser(UserEntity userEntity){
        User user = new User();
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setAlias(userEntity.getAlias());
        user.setGender(userEntity.getGender());
        user.setMobile(userEntity.getMobile());
        user.setOpenid(userEntity.getOpenid());
        user.setState(userEntity.getState());
        user.setToken(userEntity.getToken());
        return user;
    }

    public Response checkToken(String token) {
        Optional<UserEntity> userEntity = userRepository.findByToken(token);
        if (userEntity.isPresent()) {
            UserEntity user = userMessageFilter(userEntity.get());
            return Response.success(user);
        }
        return Response.fail("TOKEN_ERROR");
    }

    /*
    将用户的一些敏感信息去除
     */
    public UserEntity userMessageFilter(UserEntity user) {
        user.setPassword(null);
        user.setSalt(null);
        return user;
    }

    public Response findAllPage(Pageable pageable){
        Page<UserEntity> userEntityPage = userRepository.findAll(pageable);
        userEntityPage.getContent().forEach((k)->{k = userMessageFilter(k);});
        return Response.success(userEntityPage);
    }

    public Response findAll(){
        List<UserEntity> userEntities = userRepository.findAll();
        userEntities.forEach((k)->{k = userMessageFilter(k);});
        return Response.success(userEntities);
    }

    @Transactional
    public Response userDel(int id){
        userRepository.deleteById(id);
        return Response.success("ok");
    }

    @Transactional
    public Response userStop(int id){
        int num = userRepository.stop(id);
        return Response.success(num);
    }

    @Transactional
    public Response userRestart(int id){
        int num = userRepository.restart(id);
        return Response.success(num);
    }

    @Transactional
    public Response userAdd(UserEntity userEntity) throws Exception {
        String salt = BaseUtil.getRandomString(20,Constant.TYPE_MIX);
        String password = BaseUtil.encrypt(userEntity.getPassword(),salt);
        int num = userRepository.add(userEntity.getUsername(),
                userEntity.getMobile(),
                password,
                salt,
                userEntity.getAlias(),
                userEntity.getGender());
        return Response.success(num);
    }

    @Transactional
    public Response userModify(UserEntity userEntity){
        Optional<UserEntity> userEntityOptional = userRepository.findById(userEntity.getId());
        if (!userEntityOptional.isPresent()) {
            Response.fail("NO_RECORD");
        }
        UserEntity oldOne = userEntityOptional.get();
        oldOne.setUsername(userEntity.getUsername());
        oldOne.setMobile(userEntity.getMobile());
        oldOne.setAlias(userEntity.getAlias());
        oldOne.setGender(userEntity.getGender());
        oldOne.setUpdateTime(DateUtil.getSystemTime(Constant.DATE_FORMATER_WITH_HYPHEN));
        UserEntity newOne = userRepository.saveAndFlush(oldOne);
        return Response.success(newOne);
    }

    public Response findUserMenus(int userId){
        List<UserMenuEntity> userMenuEntities = userMenuRepository.findByUserId(userId);
        return Response.success(userMenuEntities);
     }

    @Transactional
    public Response setUserMenus(String menus) {
        JSONObject obj = JSONObject.parseObject(menus);
        List<Integer> subtractMenus = (List<Integer>)obj.get("subtractMenus");
        userMenuRepository.deleteByMenuCodeIn(subtractMenus);
        Object addMenus = obj.get("addMenus");
        List<UserMenuEntity> userMenuEntities = JSONObject.parseArray(
                JSONObject.toJSONString(addMenus), UserMenuEntity.class);
        userEntityManager.userMenuAddBatch(userMenuEntities);
        return Response.success("ok");
    }
}
