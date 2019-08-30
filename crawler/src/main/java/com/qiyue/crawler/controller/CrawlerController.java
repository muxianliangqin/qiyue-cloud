package com.qiyue.crawler.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiyue.crawler.dao.entity.CategoryEntity;
import com.qiyue.crawler.dao.entity.WebEntity;
import com.qiyue.crawler.self.Response;
import com.qiyue.crawler.self.entity.CrawlerResult;
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
    public Response findAllByUserIdAndState(@RequestParam(value = "userIds") List<Integer> userIds,
                             @PageableDefault(sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable){
        return crawlerService.findByUserIdInAndState(userIds,pageable);
    }

    @RequestMapping("/findNews")
    public Response findByCategoryId(@RequestParam(value = "categoryId") int categoryId,
                                     @PageableDefault(sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable){
        return crawlerService.findByCategoryIdAndState(categoryId,pageable);
    }

    @RequestMapping("/deleteWeb")
    public Response deleteWeb(int webId){
        return crawlerService.deleteWeb(webId);
    }

    @RequestMapping("/addWeb")
    public Response addWeb(String title, String url, int userId){
        return crawlerService.addWeb(title, url, userId);
    }

    @RequestMapping("/modifyWeb")
    public Response modifyWeb(WebEntity webEntity){
        return crawlerService.modifyWeb(webEntity);
    }

    @RequestMapping("/deleteCategory")
    public Response deleteCategory(int categoryId){
        return crawlerService.deleteCategory(categoryId);
    }

    @RequestMapping("/addCategory")
    public Response addCategory(String title, String url, String xpathTitle,String xpathText, String charset, int webId){
        return crawlerService.addCategory(title, url, xpathTitle, xpathText, charset, webId);
    }

    @RequestMapping("/modifyCategory")
    public Response modifyCategory(CategoryEntity categoryEntity){
        return crawlerService.modifyCategory(categoryEntity);
    }

    @RequestMapping("/plugin/save")
    public Response pluginResultSave(@RequestParam("crawlerResult") String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        CrawlerResult crawlerResult = JSONObject.toJavaObject(jsonObject, CrawlerResult.class);
        return crawlerService.pluginResultSave(crawlerResult);
    }

    @RequestMapping("/categoryHasRead")
    public Response categoryHasRead(int categoryId) {
        return crawlerService.categoryHasRead(categoryId);
    }

    @RequestMapping("/newsHasRead")
    public Response newsHasRead(int newsId) {
        return crawlerService.newsHasRead(newsId);
    }
}
