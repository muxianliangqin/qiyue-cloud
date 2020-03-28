package com.qiyue.user.service;

import com.qiyue.user.dao.entity.MenuEntity;
import com.qiyue.user.dao.entity.MenuLoanEntity;
import com.qiyue.service.response.Response;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface MenuService {

    Response getMenuNode(int userId);

    Response findAll(Pageable pageable);

    Response menuFindAll();

    Response menuDel(int id);

    Response menuStop(int id);

    Response menuRestart(int id);

    Response menuDelBatch(List<Integer> ids);

    Response menuStopBatch(List<Integer> ids);

    Response menuRestartBatch(List<Integer> ids);

    Response menuAdd(MenuEntity menuEntity);

    Response menuModify(MenuEntity menuEntity);

    Response menuLoanAddBatch(List<MenuLoanEntity> menuLoanEntities);
}
