package com.qiyue.wechat.controller;

import com.qiyue.wechat.dao.entity.RecordEntity;
import com.qiyue.wechat.self.Response;
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

    @RequestMapping("/findGroups")
    public Response findByUserIdAndState(@RequestParam(value = "userId") int userId,
                                         @PageableDefault(sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return weChatService.findGroups(userId, pageable);
    }

    @RequestMapping("/findRecords")
    public Response findByGroupNickName(@RequestParam(value = "groupId") int groupId,
                                        @PageableDefault(sort = {"recordTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return weChatService.findRecords(groupId, pageable);
    }

    @RequestMapping("/delete")
    public Response delete(@RequestParam(value = "groupId") int id) {
        return weChatService.delete(id);
    }

    @RequestMapping("/add")
    public Response add(@RequestParam(value = "userId") int userId,
                        @RequestParam(value = "groupNickName") String groupNickName) {
        return weChatService.add(userId, groupNickName);
    }

    @RequestMapping("/modify")
    public Response modify(@RequestParam(value = "id") int id,
                           @RequestParam(value = "groupNickName") String groupNickName) {
        return weChatService.modify(id, groupNickName);
    }

}
