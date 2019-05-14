package com.qiyue.wechat.controller;

import com.qiyue.wechat.dao.entity.UserRefWeChatEntity;
import com.qiyue.wechat.dao.entity.WeChatRecordEntity;
import com.qiyue.wechat.node.Menu;
import com.qiyue.wechat.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeChatController {
    @Autowired
    private WeChatService weChatService;

    @RequestMapping("/getMenuNode")
    public String getMenuNode(int userId){
        return weChatService.getMenuNode(userId);
    }

    @RequestMapping("/findGroups")
    public Page<UserRefWeChatEntity> findByUserIdAndState(@RequestParam(value = "userId") int userId,
                                                        @PageableDefault(sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable){
        return weChatService.findByUserIdAndState(userId,pageable);
    }

    @RequestMapping("/countGroups")
    public long countGroups(@RequestParam(value = "userId") int userId) {
        return weChatService.countGroups(userId);
    }

    @RequestMapping("/findRecords")
    public Page<WeChatRecordEntity> findByGroupNickName(@RequestParam(value = "groupNickName") String groupNickName,
                                                      @PageableDefault(sort = {"recordTime"}, direction = Sort.Direction.DESC) Pageable pageable){
        return weChatService.findByGroupNickName(groupNickName,pageable);
    }

    @RequestMapping("/countRecords")
    public long countTotalNum(String groupNickName){
        return weChatService.countTotalNum(groupNickName);
    }

    @RequestMapping("/delete")
    public int deleteCategory(String categoryId){
        return weChatService.delete(categoryId);
    }

    @RequestMapping("/modify")
    public UserRefWeChatEntity modify(Menu menu){
        return weChatService.modify(menu);
    }
}
