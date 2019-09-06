package com.qiyue.user.service;

import com.qiyue.common.constant.Constant;
import com.qiyue.user.dao.em.UserEntityManager;
import com.qiyue.user.dao.entity.MenuEntity;
import com.qiyue.user.dao.entity.MenuLoanEntity;
import com.qiyue.user.dao.repository.MenuRepository;
import com.qiyue.user.dao.repository.MenuLoanRepository;
import com.qiyue.common.node.Node;
import com.qiyue.common.redis.RedisUtil;
import com.qiyue.common.response.Response;
import com.qiyue.common.util.DateUtil;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuLoanRepository menuLoanRepository;

    @Autowired
    private UserEntityManager userEntityManager;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RedisUtil redisUtil;

    public Response getMenuNode(int userId){
        Filter filter = entityManager.unwrap(Session.class).enableFilter("userIdFilter");
        filter.setParameter("userId", userId);
        List<MenuEntity> menuEntities = menuRepository.getMenus(userId);
        return Response.success(Node.insertBatch(menuEntities));
    }

    public Response findAll(Pageable pageable){
        Page<MenuEntity> menuEntityPage = menuRepository.findAll(pageable);
        return Response.success(menuEntityPage);
    }

    public Response menuFindAll(){
        List<MenuEntity> menuEntities = menuRepository.findAll();
        return Response.success(Node.insertBatch(menuEntities));
    }

    @Transactional
    public Response menuDel(int id){
        menuRepository.deleteById(id);
        return Response.success("ok");
    }

    @Transactional
    public Response menuStop(int id){
        int num = menuRepository.stop(id);
        return Response.success(num);
    }

    @Transactional
    public Response menuRestart(int id){
        int num = menuRepository.restart(id);
        return Response.success(num);
    }

    @Transactional
    public Response menuDelBatch(List<Integer> ids){
        int num = menuRepository.delBatch(ids);
        return Response.success(num);
    }

    @Transactional
    public Response menuStopBatch(List<Integer> ids){
        int num = menuRepository.stopBatch(ids);
        return Response.success(num);
    }

    @Transactional
    public Response menuRestartBatch(List<Integer> ids){
        int num = menuRepository.restartBatch(ids);
        return Response.success(num);
    }

    @Transactional
    public Response menuAdd(MenuEntity menuEntity){
        int num = menuRepository.add(menuEntity.getCode(), menuEntity.getName(),
                menuEntity.getUrl(),menuEntity.getSuperCode());
        return Response.success(num);
    }

    @Transactional
    public Response menuModify(MenuEntity menuEntity){
        Optional<MenuEntity> menuEntityOptional = menuRepository.findById(menuEntity.getId());
        if (!menuEntityOptional.isPresent()) {
            Response.fail("NO_RECORD");
        }
        MenuEntity oldOne = menuEntityOptional.get();
        oldOne.setCode(menuEntity.getCode());
        oldOne.setName(menuEntity.getName());
        oldOne.setSuperCode(menuEntity.getSuperCode());
        oldOne.setUpdateTime(DateUtil.getSystemTime(Constant.DATE_FORMATER_WITH_HYPHEN));
        MenuEntity newOne = menuRepository.saveAndFlush(oldOne);
        return Response.success(newOne);
    }

    public Response menuLoanAddBatch(List<MenuLoanEntity> menuLoanEntities) {
        if (menuLoanEntities.size() > 0) {
            menuLoanRepository.deleteByUserId(menuLoanEntities.get(0).getUserId());
            return userEntityManager.menuLoanAddBatch(menuLoanEntities);
        } else {
            return Response.fail("MENU_LOAN_INSERT_EMPTY_INPUT_ERROR");
        }

    }
}
