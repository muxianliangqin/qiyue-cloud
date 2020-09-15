package com.qiyue.user.service;

import com.qiyue.base.model.request.Request;
import com.qiyue.base.model.response.Response;
import com.qiyue.user.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    /**
     * 登录
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param request
     * @return
     */
    Response login(Request<UserEntity> request,
                   HttpServletRequest httpServletRequest,
                   HttpServletResponse httpServletResponse);

    /**
     * 登出
     *
     * @param request
     * @param response
     * @return
     */
    Response logout(HttpServletRequest request, HttpServletResponse response);

}
