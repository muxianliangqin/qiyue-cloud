package com.qiyue.user.service;

import com.qiyue.user.constant.Constant;
import com.qiyue.user.dao.entity.MenuEntity;
import com.qiyue.user.dao.entity.MenuLoanEntity;
import com.qiyue.user.dao.repository.MenuRepository;
import com.qiyue.user.dao.repository.MenuLoanRepository;
import com.qiyue.user.node.Node;
import com.qiyue.user.self.Response;
import com.qiyue.user.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuLoanRepository menuLoanRepository;

    public Response getMenuNode(int userId){
        List<MenuEntity> menuEntities = menuRepository.getMenus(userId);
        menuEntities.forEach(v -> {
            if (v.getMenuLoanEntities().size() > 0) {
                v.getMenuLoanEntities().stream().filter(k -> {return userId == k.getUserId();});
            }
        });
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

}
