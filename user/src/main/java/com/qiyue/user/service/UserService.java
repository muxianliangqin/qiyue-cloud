package com.qiyue.user.service;

import com.qiyue.base.model.request.Request;
import com.qiyue.base.model.response.Response;
import com.qiyue.user.model.vo.UserInfoVO;
import com.qiyue.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    Response userAdd(Request<UserEntity> request) throws Exception;

    Response userDel(Request<UserEntity> request);

    Response userStop(Request<UserEntity> request);

    Response userRestart(Request<UserEntity> request);

    Response userModify(Request<UserEntity> request);

    Response findUserMenus(Request<UserEntity> request);

    Response<Page<UserInfoVO>> findAllPage(Pageable pageable);

    Response<List<UserInfoVO>> findAll();

    Response<List<UserInfoVO>> findByUsernameLike(Request<String> request);
}
