package com.qiyue.crawler.controller;

import com.qiyue.crawler.dao.entity.CategoryEntity;
import com.qiyue.crawler.dao.entity.NewsEntity;
import com.qiyue.crawler.node.Menu;
import com.qiyue.crawler.service.CrawlerService;
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
public class CrawlerController {
    @Autowired
    private CrawlerService crawlerService;

    @RequestMapping("/getMenuNode")
    public String getMenuNode(){
        return crawlerService.getMenuNode();
    }

    @RequestMapping("/findNews")
    public Page<NewsEntity> findByCategoryUrl(@RequestParam(value = "categoryUrl") String categoryUrl,
                                               @PageableDefault(sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable){
        return crawlerService.findByCategoryUrlPage(categoryUrl,pageable);
    }

    @RequestMapping("/totalNum")
    public long countTotalNum(String categoryUrl){
        return crawlerService.countTotalNum(categoryUrl);
    }

    @RequestMapping("/deleteCategory")
    public int deleteCategory(String categoryId){
        return crawlerService.deleteCategory(categoryId);
    }

    @RequestMapping("/ModifyCategory")
    public CategoryEntity ModifyCategory(Menu menu){
        return crawlerService.ModifyCategory(menu);
    }
}