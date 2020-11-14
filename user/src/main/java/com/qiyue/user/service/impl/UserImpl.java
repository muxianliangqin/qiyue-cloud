package com.qiyue.user.service.impl;

import com.qiyue.base.constant.Constant;
import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.DatabaseException;
import com.qiyue.base.model.request.Request;
import com.qiyue.base.model.response.Response;
import com.qiyue.base.utils.BaseUtil;
import com.qiyue.base.utils.ParamVerify;
import com.qiyue.base.utils.StringUtil;
import com.qiyue.base.utils.encrypt.CipherUtil;
import com.qiyue.user.constant.UserConstant;
import com.qiyue.user.dao.entity.UserDao;
import com.qiyue.user.entity.UserEntity;
import com.qiyue.user.enums.DataStateEnum;
import com.qiyue.user.model.vo.UserInfoVO;
import com.qiyue.user.service.UserService;
import com.qiyue.user.utils.IdUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private static String STATE = "state";

    /* 增 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> userAdd(Request<UserEntity> request) {
        UserEntity userEntity = request.getParams();
        ParamVerify.isNotNull(userEntity.getUsername(), "用户名称", "username");
        ParamVerify.isNotNull(userEntity.getMobile(), "手机号码", "mobile");
        ParamVerify.isNotNull(userEntity.getAlias(), "昵称", "alias");
        ParamVerify.isNotNull(userEntity.getGender(), "性别", "gender");
        ParamVerify.isNotNull(userEntity.getPassword(), "密码", "password");
        userEntity.setUserId(IdUtil.nextId());
        String salt = BaseUtil.getRandomString(UserConstant.SALT_LENGTH, Constant.TYPE_MIX);
        String password = CipherUtil.SHA.encrypt(userEntity.getPassword(), salt);
        userEntity.setSalt(salt);
        userEntity.setPassword(password);
        userDao.save(userEntity);
        return Response.success();
    }

    /* 删 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> userDel(Request<UserEntity> request) {
        UserEntity userEntity = request.getParams();
        ParamVerify.isNotNull(userEntity.getUserId(), "用户ID", "userId");
        userDao.deleteByUserId(userEntity.getUserId());
        return Response.success();
    }

    /* 改 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> userStop(Request<UserEntity> request) {
        UserEntity params = request.getParams();
        ParamVerify.isNotNull(params.getUserId(), "用户ID", "userId");
        UserEntity userEntity = userDao.findByUserId(params.getUserId()).orElseThrow(() ->
                new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "userId", params.getUserId())
        );
        userEntity.setState(DataStateEnum.UNUSABLE.getState());
        userDao.save(userEntity);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> userRestart(Request<UserEntity> request) {
        UserEntity params = request.getParams();
        ParamVerify.isNotNull(params.getUserId(), "用户ID", "userId");
        UserEntity userEntity = userDao.findByUserId(params.getUserId()).orElseThrow(() ->
                new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "userId", params.getUserId())
        );
        userEntity.setState(DataStateEnum.USABLE.getState());
        userDao.save(userEntity);
        return Response.success();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> userModify(Request<UserEntity> request) {
        UserEntity userEntity = request.getParams();
        ParamVerify.isNotNull(userEntity.getUsername(), "用户名称", "username");
        ParamVerify.isNotNull(userEntity.getMobile(), "手机号码", "mobile");
        ParamVerify.isNotNull(userEntity.getAlias(), "昵称", "alias");
        ParamVerify.isNotNull(userEntity.getGender(), "性别", "gender");
        UserEntity oldOne = userDao.findByUserId(userEntity.getUserId()).orElseThrow(() ->
                new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "id", userEntity.getUserId())
        );
        if (StringUtils.isNotEmpty(userEntity.getPassword())) {
            String salt = BaseUtil.getRandomString(UserConstant.SALT_LENGTH, Constant.TYPE_MIX);
            String password = CipherUtil.SHA.encrypt(userEntity.getPassword(), salt);
            oldOne.setSalt(salt);
            oldOne.setPassword(password);
        }
        oldOne.setUsername(userEntity.getUsername());
        oldOne.setMobile(userEntity.getMobile());
        oldOne.setAlias(userEntity.getAlias());
        oldOne.setGender(userEntity.getGender());
        userDao.save(oldOne);
        return Response.success();
    }

    /* 查 */
    @Override
    public Response<Page<UserInfoVO>> findAllPage(Pageable pageable) {
        Page<UserEntity> userEntityPage = userDao.findAll(pageable);
        Page<UserInfoVO> userPage = userEntityPage.map(UserInfoVO::transform);
        return Response.success(userPage);
    }

    @Override
    public Response<List<UserInfoVO>> findAll() {
        List<UserEntity> userEntityList = userDao.findAll();
        return Response.success(UserInfoVO.transform(userEntityList));
    }

    @Override
    public Response<List<UserInfoVO>> findByUsernameLike(Request<String> request) {
        String username = request.getParams();
        username= StringUtil.format("%{}%", username);
        List<UserEntity> userEntities = userDao.findByUsernameLike(username);
        return Response.success(UserInfoVO.transform(userEntities));
    }

    /**
     * 获取用户有权限的菜单
     *
     * @param request
     * @return
     */
    @Override
    public Response<String> findUserMenus(Request<UserEntity> request) {
//        List<UserMenuEntity> userMenuEntities = userMenuRepository.findByUserId(userId);
//        return Response.success(userMenuEntities);
        return Response.success();
    }

}
