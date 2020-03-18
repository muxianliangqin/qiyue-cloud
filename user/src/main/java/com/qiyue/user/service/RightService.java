package com.qiyue.user.service;

import com.qiyue.user.dao.entity.RightEntity;
import com.qiyue.common.response.Response;
import org.springframework.data.domain.Pageable;


public interface RightService {

    Response rightFindAll(Pageable pageable);

    Response rightDel(int id);

    Response rightStop(int id);

    Response rightRestart(int id);

    Response rightAdd(RightEntity rightEntity);

    Response rightModify(RightEntity RightEntity);

    Response rightMenu(String rightCode);
}
