package com.qiyue.user.model.vo;

import com.qiyue.base.utils.EnumUtil;
import com.qiyue.user.entity.UserEntity;
import com.qiyue.user.enums.DataStateEnum;
import com.qiyue.user.enums.UserGenderEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserInfoVO implements Serializable {

    private Long userId;

    private String mobile;

    private String email;

    private String username;

    private String alias;

    private Integer gender;

    private String genderName;

    private String openid;

    private Integer state;

    private String stateName;

    private List<RoleVO> roles = new ArrayList<>();

    public static UserInfoVO transform(UserEntity userEntity) {
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userEntity, userInfoVO);
        userInfoVO.setStateName(EnumUtil.convert(userInfoVO.getState(), DataStateEnum.values(), DataStateEnum::getState, DataStateEnum::getMsg));
        userInfoVO.setGenderName(EnumUtil.convert(userInfoVO.getGender(), UserGenderEnum.values(), UserGenderEnum::getType, UserGenderEnum::getMsg));
        return userInfoVO;
    }

    public static List<UserInfoVO> transform(List<UserEntity> userEntityList) {
        if (CollectionUtils.isEmpty(userEntityList)) {
            return new ArrayList<>();
        }
        return userEntityList.stream().map(UserInfoVO::transform).collect(Collectors.toList());
    }

    @Data
    public static class RoleVO {

        private Long roleId;

        private String name;

        private String desc;

        private Integer state;

        private String stateName;

    }
}
