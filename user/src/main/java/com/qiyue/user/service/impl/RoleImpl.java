package com.qiyue.user.service.impl;

import com.qiyue.base.constant.ErrorConstant;
import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.DatabaseException;
import com.qiyue.base.utils.ParamVerify;
import com.qiyue.base.model.response.Response;
import com.qiyue.service.utils.ContextUtil;
import com.qiyue.user.entity.RoleEntity;
import com.qiyue.user.entity.RoleMenuEntity;
import com.qiyue.user.dao.entity.RoleMenuDao;
import com.qiyue.user.dao.entity.RoleDao;
import com.qiyue.user.enums.DataStateEnum;
import com.qiyue.user.model.param.RoleMenusParam;
import com.qiyue.user.service.RoleService;
import com.qiyue.user.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public Response roleFindAll(Pageable pageable) {
        Page<RoleEntity> rightEntityPage = roleDao.findAll(pageable);
        return Response.success(rightEntityPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> roleDel(Long roleId) {
        RoleEntity roleEntity = roleDao.findByRoleId(roleId).orElseThrow(() ->
                new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "roleId", roleId)
        );
        roleDao.delete(roleEntity);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> roleStop(Long roleId) {
        RoleEntity roleEntity = roleDao.findByRoleId(roleId).orElseThrow(() ->
                new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "roleId", roleId)
        );
        roleEntity.setState(DataStateEnum.UNUSABLE.getState());
        roleDao.save(roleEntity);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> roleRestart(Long roleId) {
        RoleEntity roleEntity = roleDao.findByRoleId(roleId).orElseThrow(() ->
                new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "roleId", roleId)
        );
        roleEntity.setState(DataStateEnum.USABLE.getState());
        roleDao.save(roleEntity);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> roleAdd(RoleEntity roleEntity) {
        ParamVerify.isNotNull(roleEntity.getName(), "角色名称", "name");
        ParamVerify.isNotNull(roleEntity.getDesc(), "角色描述", "desc");
        roleEntity.setRoleId(IdUtil.nextId());
        roleEntity.setCreateUser(ContextUtil.getUser().getUserId());
        roleEntity.setUpdateUser(ContextUtil.getUser().getUserId());
        roleDao.save(roleEntity);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> roleModify(RoleEntity RoleEntity) {
        Optional<RoleEntity> RightEntityOptional = roleDao.findById(RoleEntity.getId());
        if (!RightEntityOptional.isPresent()) {
            Response.fail(ErrorConstant.NO_RECORD);
        }
        RoleEntity oldOne = RightEntityOptional.get();
        oldOne.setRoleId(RoleEntity.getRoleId());
        oldOne.setName(RoleEntity.getName());
        oldOne.setDesc(RoleEntity.getDesc());
        RoleEntity newOne = roleDao.saveAndFlush(oldOne);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> setRoleMenus(RoleMenusParam param) {
        ParamVerify.isNotNull(param.getRoleId(), "角色ID", "roleId");
        List<RoleMenuEntity> roleMenuAddList = RoleMenusParam.ASSEMBLE_ROLE_MENU.apply(param, RoleMenusParam::getAddMenus);
        roleMenuDao.saveAll(roleMenuAddList);
        List<RoleMenuEntity> roleMenuRemoveList = RoleMenusParam.ASSEMBLE_ROLE_MENU.apply(param, RoleMenusParam::getRemoveMenus);
        roleMenuRemoveList.forEach(k -> roleMenuDao.deleteByRoleIdAndMenuId(k.getRoleId(), k.getMenuId()));
        return Response.success();
    }


    @Override
    public Response<List<RoleMenuEntity>> roleMenu(Long roleId) {
        List<RoleMenuEntity> rightMenuEntities = roleMenuDao.findByRoleId(roleId);
        return Response.success(rightMenuEntities);
    }

    @Override
    public Response<List<RoleEntity>> findAll() {
        return Response.success(roleDao.findAll());
    }

    @Override
    public Response<List<RoleEntity>> findByUserId(Long userId) {
        return Response.success(roleDao.findByUserId(userId));
    }


}
