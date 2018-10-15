package com.qiyue.crawler.controller;

import com.qiyue.crawler.dao.entity.CategoryEntity;
import com.qiyue.crawler.dao.entity.NewsEntity;
import com.qiyue.crawler.dao.entity.WebEntity;
import com.qiyue.crawler.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CrawlerController {
    @Autowired
    private CrawlerService crawlerService;

    @RequestMapping(value = "/findAllWebs")
    public List<WebEntity> findAllWebs(){
        return crawlerService.findAllWebs();
    }

    @RequestMapping(value = "/findAllCategories")
    public List<CategoryEntity> findAllCategories(){
        return crawlerService.findAllCategories();
    }

    @RequestMapping(value = "/findNews")
    public List<NewsEntity> findByCategoryUrl(String categoryUrl){
        return crawlerService.findByCategoryUrl(categoryUrl);
    }
}
