package com.qiyue.crawler.controller;

import com.qiyue.crawler.dao.entity.CategoryEntity;
import com.qiyue.crawler.self.Response;
import com.qiyue.crawler.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/findWebs")
    public Response findWebs(@RequestParam(value = "userId") int userId,
                             @PageableDefault(sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable){
        return crawlerService.findWebs(userId,pageable);
    }

    @RequestMapping("/findNews")
    public Response findByCategoryUrl(@RequestParam(value = "categoryUrl") String categoryUrl,
                                               @PageableDefault(sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable){
        return crawlerService.findByCategoryUrlAndState(categoryUrl,pageable);
    }

    @RequestMapping("/deleteCategory")
    public int deleteCategory(String categoryId){
        return crawlerService.deleteCategory(categoryId);
    }

    @RequestMapping("/ModifyCategory")
    public CategoryEntity ModifyCategory(CategoryEntity categoryEntity){
        return crawlerService.ModifyCategory(categoryEntity);
    }
}
