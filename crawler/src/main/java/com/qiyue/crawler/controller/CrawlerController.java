package com.qiyue.crawler.controller;

import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.model.param.PluginCrawlerParam;
import com.qiyue.crawler.service.CrawlerService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CrawlerController {
    private final CrawlerService crawlerService;

    public CrawlerController(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    /**
     * 允许跨域请求本接口
     *
     * @param crawlerParam 爬取结果
     * @return 接口执行结果
     */
    @RequestMapping("/plugin/save")
    public Response<String> savePluginCrawler(@RequestBody PluginCrawlerParam crawlerParam) {
        return crawlerService.savePluginCrawler(crawlerParam);
    }

}
