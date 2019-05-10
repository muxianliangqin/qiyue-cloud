package com.qiyue.wechat.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiyue.wechat.dao.entity.UserRefWeChatEntity;
import com.qiyue.wechat.dao.entity.WeChatRecordEntity;
import com.qiyue.wechat.dao.repo.UserRefWeChatRepository;
import com.qiyue.wechat.dao.repo.WeChatRecordRepository;
import com.qiyue.wechat.except.BaseExcept;
import com.qiyue.wechat.node.Menu;
import com.qiyue.wechat.node.Node;
import com.qiyue.wechat.node.NodeTree;
import com.qiyue.wechat.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class WeChatService {

    @Autowired
    private WeChatRecordRepository weChatRecordRepository;

    @Autowired
    private UserRefWeChatRepository userRefWeChatRepository;

    public String getMenuNode(int userId){
        Node node = NodeTree.getInstance();
        List<UserRefWeChatEntity> wechatRecordEntities = userRefWeChatRepository.findByUserIdAndState(userId, "0");
        wechatRecordEntities.forEach(k->{
            Menu menu = new Menu();
            menu.setId(String.valueOf(k.getId()));
            menu.setName(k.getGroupNickName());
            NodeTree.insert(node,menu);
        });
        JSONObject json = JSON.parseObject(node.toString());
        return json.toString();
    }

    public Page<WeChatRecordEntity> findByGroupNickName(String groupNickName, Pageable pageable){
        return weChatRecordRepository.findByGroupNickName(groupNickName, pageable);
    }

    public long countTotalNum(String groupNickName){
        return weChatRecordRepository.countByGroupNickName(groupNickName);
    }

    @Transactional
    public int delete(String id) {
        return userRefWeChatRepository.updateState(Integer.parseInt(id));
    }

    @Transactional
    public UserRefWeChatEntity modify(Menu menu){
        Optional<UserRefWeChatEntity> categoryEntityOptional = userRefWeChatRepository.findById(Integer.parseInt(menu.getId()));
        if (!categoryEntityOptional.isPresent()) {
            throw new BaseExcept("0000","更新记录不存在");
        }
        UserRefWeChatEntity userRefWeChatEntity = new UserRefWeChatEntity();
        userRefWeChatEntity.setId(Integer.parseInt(menu.getId()));
        userRefWeChatEntity.setGroupNickName(menu.getName());
//        categoryEntity.setUpdateUser("");
        SqlUtil.copyNullProperties(categoryEntityOptional.get(), userRefWeChatEntity);
        UserRefWeChatEntity newOne = userRefWeChatRepository.saveAndFlush(userRefWeChatEntity);
        return newOne;
    }
}
