package com.qiyue.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qiyue.base.constant.Constant;
import com.qiyue.base.constant.ErrorConstant;
import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.BusinessException;
import com.qiyue.base.model.bo.RoleBO;
import com.qiyue.base.redis.RedisHandler;
import com.qiyue.base.model.bo.UserInfoBO;
import com.qiyue.base.utils.BaseUtil;
import com.qiyue.base.utils.EnumUtil;
import com.qiyue.base.utils.ParamVerify;
import com.qiyue.base.utils.StreamUtil;
import com.qiyue.base.utils.encrypt.CipherUtil;
import com.qiyue.base.model.request.Request;
import com.qiyue.base.model.response.Response;
import com.qiyue.user.dao.entity.RoleDao;
import com.qiyue.user.dao.entity.UserRoleDao;
import com.qiyue.user.entity.RoleEntity;
import com.qiyue.user.entity.UserEntity;
import com.qiyue.user.dao.entity.UserDao;
import com.qiyue.user.entity.UserRoleEntity;
import com.qiyue.user.enums.DataStateEnum;
import com.qiyue.user.enums.UserGenderEnum;
import com.qiyue.user.model.vo.UserInfoVO;
import com.qiyue.user.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginImpl implements LoginService {

    @Value("${login.auth.token.expires}")
    private long tokenExpires;

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RedisHandler redisHandler;

    @Override
    public Response login(Request<UserEntity> request,
                          HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse) {
        ParamVerify.isNotNull(request.getParams(), "请求参数", "params");
        UserEntity userEntity = request.getParams();
        ParamVerify.isNotNull(userEntity.getUsername(), "用户名称", "username");
        ParamVerify.isNotNull(userEntity.getUsername(), "用户密码", "password");
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        // 数据库是否存在记录，不存在则报错
        userEntity = userDao.findByUsername(username).orElseThrow(() ->
                new BusinessException(ErrorEnum.LOGIN_ERROR)
        );
        password = CipherUtil.SHA.encrypt(password, userEntity.getSalt());
        // 比较密码是否相同，不相同则报错
        if (!BaseUtil.slowEquals(password, userEntity.getPassword())) {
            return Response.fail(ErrorConstant.LOGIN_ERROR);
        }
        // 在response中设置token
        String token = BaseUtil.getRandomString(Constant.TOKEN_LENGTH, Constant.TYPE_MIX);
        httpServletResponse.setHeader("Access-Control-Expose-Headers", Constant.TOKEN_NAMESPACE);
        httpServletResponse.addHeader(Constant.TOKEN_NAMESPACE, token);
        // 获取用户的角色信息
        List<UserRoleEntity> userRoleEntityList = userRoleDao.findByUserIdAndState(userEntity.getUserId(), DataStateEnum.USABLE.getState());
        List<RoleEntity> roleEntityList = roleDao.findByRoleIdInAndState(StreamUtil.pickSpecific(userRoleEntityList, UserRoleEntity::getRoleId), DataStateEnum.USABLE.getState());
        // 将实体类转为BO(businessObject)业务对象，方便其他service使用
        UserInfoBO userInfoBO = new UserInfoBO();
        BeanUtils.copyProperties(userEntity, userInfoBO);
        List<RoleBO> roleBOList = roleEntityList.stream().map(k -> {
            RoleBO roleBO = new RoleBO();
            BeanUtils.copyProperties(k, roleBO);
            return roleBO;
        }).collect(Collectors.toList());
        userInfoBO.setRoles(roleBOList);
        // redis保存用户信息
        redisHandler.getHashTemplate().set(Constant.TOKEN_NAMESPACE, token, userInfoBO, tokenExpires);
        // 处理部分敏感数据及转义状态值
        UserInfoVO userInfoVO = JSONObject.parseObject(JSONObject.toJSONString(userInfoBO), UserInfoVO.class);
        userInfoVO.setStateName(EnumUtil.convert(userInfoVO.getState(), DataStateEnum.values(), DataStateEnum::getState, DataStateEnum::getMsg));
        userInfoVO.setGenderName(EnumUtil.convert(userInfoVO.getGender(), UserGenderEnum.values(), UserGenderEnum::getType, UserGenderEnum::getMsg));
        userInfoVO.getRoles().forEach(k -> {
            k.setStateName(EnumUtil.convert(k.getState(), DataStateEnum.values(), DataStateEnum::getState, DataStateEnum::getMsg));
        });
        return Response.success(userInfoVO);
    }

    @Override
    public Response logout(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(Constant.TOKEN_NAMESPACE);
        if (StringUtils.isNoneEmpty(token)) {
            redisHandler.getHashTemplate().delete(Constant.TOKEN_NAMESPACE, token);
        }
        response.setStatus(Constant.LOGOUT_HTTP_STATUS);
        return Response.success();
    }

}
