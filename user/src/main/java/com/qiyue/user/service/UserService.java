package com.qiyue.user.service;

import com.qiyue.user.dao.entity.UserEntity;
import com.qiyue.service.response.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public interface UserService {

    Response login(HttpServletRequest request,
                   HttpServletResponse response,
                   String username, String password) throws Exception;

    Response logout(HttpServletRequest request, HttpServletResponse response);

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
