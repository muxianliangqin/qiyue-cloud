package com.qiyue.wechat.service;

import com.qiyue.wechat.dao.entity.GroupEntity;
import com.qiyue.wechat.dao.entity.RecordEntity;
import com.qiyue.wechat.dao.repo.GroupRepository;
import com.qiyue.wechat.dao.repo.RecordRepository;
import com.qiyue.wechat.except.BaseExcept;
import com.qiyue.wechat.self.Response;
import com.qiyue.wechat.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class WeChatService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private GroupRepository groupRepository;

    public Response findGroups(int userId, Pageable pageable) {
        Page<GroupEntity> groupEntityPage = groupRepository.findByUserIdAndState(userId, "0", pageable);
        Response response = Response.success(groupEntityPage);
        return response;
    }

    public Response findRecords(int groupId, Pageable pageable){
        Page<RecordEntity> recordEntityPage = recordRepository.findByGroupId(groupId, pageable);
        Response response = Response.success(recordEntityPage);
        return response;
    }

    @Transactional
    public int delete(int id) {
        return groupRepository.updateState(id);
    }

    @Transactional
    public int add(int userId,String groupNickName) {
        return groupRepository.add(userId,groupNickName);
    }

    @Transactional
    public GroupEntity modify(GroupEntity groupEntity){
        Optional<GroupEntity> categoryEntityOptional = groupRepository.findById(groupEntity.getId());
        if (!categoryEntityOptional.isPresent()) {
            throw new BaseExcept("0000","更新记录不存在");
        }
//        categoryEntity.setUpdateUser("");
        SqlUtil.copyNullProperties(categoryEntityOptional.get(), groupEntity);
        GroupEntity newOne = groupRepository.saveAndFlush(groupEntity);
        return newOne;
    }
}
