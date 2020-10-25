package com.qiyue.crawler.controller;

import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.model.param.PluginCrawlerParam;
import com.qiyue.crawler.service.CrawlerService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @CrossOrigin(allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
    @RequestMapping("/plugin/save")
    public Response<String> savePluginCrawler(@RequestBody PluginCrawlerParam crawlerParam,
                                              HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return crawlerService.savePluginCrawler(crawlerParam);
    }

}
