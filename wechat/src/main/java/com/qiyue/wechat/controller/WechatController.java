package com.qiyue.wechat.controller;

import com.qiyue.wechat.dao.entity.UserRefWechatEntity;
import com.qiyue.wechat.dao.entity.WechatRecordEntity;
import com.qiyue.wechat.node.Menu;
import com.qiyue.wechat.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WechatController {
    @Autowired
    private WechatService wechatService;

    @RequestMapping("/getMenuNode")
    public String getMenuNode(){
        return wechatService.getMenuNode();
    }

    @RequestMapping("/findNews")
    public Page<NewsEntity> findByCategoryUrl(@RequestParam(value = "categoryUrl") String categoryUrl,
                                               @PageableDefault(sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable){
        return wechatService.findByCategoryUrlPage(categoryUrl,pageable);
    }

    @RequestMapping("/totalNum")
    public long countTotalNum(String categoryUrl){
        return wechatService.countTotalNum(categoryUrl);
    }

    @RequestMapping("/deleteCategory")
    public int deleteCategory(String categoryId){
        return wechatService.deleteCategory(categoryId);
    }

    @RequestMapping("/ModifyCategory")
    public CategoryEntity ModifyCategory(Menu menu){
        return wechatService.ModifyCategory(menu);
    }
}
