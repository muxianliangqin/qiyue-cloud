package com.qiyue.user.service;

import com.alibaba.fastjson.JSONObject;
import com.qiyue.common.constant.ErrorConstant;
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
public interface UserService {

    Response login(HttpServletRequest request,
                          String username, String password) throws Exception;

    Response logout(HttpServletRequest request);

    Response checkToken(String token);

    Response findAllPage(Pageable pageable);

    Response findAll();

    Response userDel(int id);

    Response userStop(int id);

    Response userRestart(int id);

    Response userAdd(UserEntity userEntity) throws Exception;

    Response userModify(UserEntity userEntity);

    Response findUserMenus(int userId);

    Response setUserMenus(String menus);
}
