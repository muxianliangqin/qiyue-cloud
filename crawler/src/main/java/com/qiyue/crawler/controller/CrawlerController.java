package com.qiyue.crawler.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.model.param.PluginCrawlerParam;
import com.qiyue.crawler.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlerController {
    @Autowired
    private CrawlerService crawlerService;

    @RequestMapping("/plugin/save")
    public Response savePluginCrawler(@RequestBody PluginCrawlerParam crawlerParam) {
        return crawlerService.savePluginCrawler(crawlerParam);
    }

}
