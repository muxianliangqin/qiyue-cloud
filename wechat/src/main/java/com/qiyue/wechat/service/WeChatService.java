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
        return Response.success(recordEntityPage);
    }

    @Transactional
    public Response delete(int id) {
        long num = groupRepository.updateState(id);
        if (num > 0) {
            return Response.success(num);
        } else {
            return Response.fail("NO_RECORD");
        }
    }

    @Transactional
    public Response add(int userId, String groupNickName) {
        long num = groupRepository.add(userId,groupNickName);
        return Response.success(num);
    }

    @Transactional
    public Response modify(int id, String groupNickName){
        long num = groupRepository.modify(id, groupNickName);
        if (num > 0) {
            return Response.success(num);
        } else {
            return Response.fail("NO_RECORD");
        }
    }
}
