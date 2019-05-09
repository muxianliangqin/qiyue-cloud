package com.qiyue.wechat.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiyue.wechat.constant.Constant;
import com.qiyue.wechat.dao.entity.UserRefWechatEntity;
import com.qiyue.wechat.dao.entity.WechatRecordEntity;
import com.qiyue.wechat.dao.repo.UserRefWechatRepository;
import com.qiyue.wechat.dao.repo.WechatRecordRepository;
import com.qiyue.wechat.except.BaseExcept;
import com.qiyue.wechat.node.Menu;
import com.qiyue.wechat.node.Node;
import com.qiyue.wechat.node.NodeTree;
import com.qiyue.wechat.util.DateUtil;
import com.qiyue.wechat.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WechatService {

    @Autowired
    private WechatRecordRepository wechatRecordRepository;

    @Autowired
    private UserRefWechatRepository userRefWechatRepository;

    public String getMenuNode(String userId){
        Node node = NodeTree.getInstance();
        List<UserRefWechatEntity> wechatRecordEntities = userRefWechatRepository.findByUserIdAndState(userId, "0");
        wechatRecordEntities.forEach(k->{
            Menu menu = new Menu();
            menu.setId("group_" + k.getId());
            menu.setName(k.getGroupNickName());
            NodeTree.insert(node,menu);
        });
        JSONObject json = JSON.parseObject(node.toString());
        return json.toString();
    }

    public Page<WechatRecordEntity> findByGroupNickNameAndState(String groupNickName,Pageable pageable){
        return wechatRecordRepository.findByGroupNickNameAndState(groupNickName,"0", pageable);
    }

    public long countTotalNum(String categoryUrl){
        return wechatRecordRepository.countByGroupNickNameAndState(categoryUrl,"0");
    }

    @Transactional
    public int deleteCategory(String id) {
        return userRefWechatRepository.updateState(Integer.parseInt(id));
    }

    @Transactional
    public UserRefWechatEntity ModifyGroupName(Menu menu){
        Optional<UserRefWechatEntity> categoryEntityOptional = userRefWechatRepository.findById(Integer.parseInt(menu.getId()));
        if (!categoryEntityOptional.isPresent()) {
            throw new BaseExcept("0000","更新记录不存在");
        }
        UserRefWechatEntity userRefWechatEntity = new UserRefWechatEntity();
        userRefWechatEntity.setId(Integer.parseInt(menu.getId()));
        userRefWechatEntity.setGroupNickName(menu.getName());
//        categoryEntity.setUpdateUser("");
        SqlUtil.copyNullProperties(categoryEntityOptional.get(),userRefWechatEntity);
        UserRefWechatEntity newOne = userRefWechatRepository.saveAndFlush(userRefWechatEntity);
        return newOne;
    }
}
