package com.qiyue.user.service;

import com.qiyue.common.constant.Constant;
import com.qiyue.user.dao.entity.RightEntity;
import com.qiyue.user.dao.entity.RightMenuEntity;
import com.qiyue.user.dao.repository.RightMenuRepository;
import com.qiyue.user.dao.repository.RightRepository;
import com.qiyue.common.response.Response;
import com.qiyue.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RightService {
    @Autowired
    private RightRepository rightRepository;

    @Autowired
    private RightMenuRepository rightMenuRepository;

    public Response rightFindAll(Pageable pageable){
        Page<RightEntity> rightEntityPage = rightRepository.findAll(pageable);
        return Response.success(rightEntityPage);
    }

    @Transactional
    public Response rightDel(int id){
        rightRepository.deleteById(id);
        return Response.success("ok");
    }

    @Transactional
    public Response rightStop(int id){
        int num = rightRepository.stop(id);
        return Response.success(num);
    }

    @Transactional
    public Response rightRestart(int id){
        int num = rightRepository.restart(id);
        return Response.success(num);
    }

    @Transactional
    public Response rightAdd(RightEntity rightEntity){
        int num = rightRepository.add(rightEntity.getCode(),
                rightEntity.getName(),
                rightEntity.getDesc());
        return Response.success(num);
    }

    @Transactional
    public Response rightModify(RightEntity RightEntity){
        Optional<RightEntity> RightEntityOptional = rightRepository.findById(RightEntity.getId());
        if (!RightEntityOptional.isPresent()) {
            Response.fail("NO_RECORD");
        }
        RightEntity oldOne = RightEntityOptional.get();
        oldOne.setCode(RightEntity.getCode());
        oldOne.setName(RightEntity.getName());
        oldOne.setDesc(RightEntity.getDesc());
        oldOne.setUpdateTime(DateUtil.getSystemTime(Constant.DATE_FORMATER_WITH_HYPHEN));
        RightEntity newOne = rightRepository.saveAndFlush(oldOne);
        return Response.success(newOne);
    }

    public Response rightMenu(String rightCode) {
        List<RightMenuEntity> rightMenuEntities =  rightMenuRepository.findByRightCode(rightCode);
        return Response.success(rightMenuEntities);
    }
}
