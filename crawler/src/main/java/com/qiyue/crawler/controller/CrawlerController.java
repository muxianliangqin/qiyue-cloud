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
     * <p>
     * 在这里@CrossOrigin只是为了解决本地测试的跨域，
     * 生产环境使用nginx处理跨域，应去除本注解，不能重复设置
     *
     * @param crawlerParam 爬取结果
     * @return 接口执行结果
     */
    @CrossOrigin(allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
    @RequestMapping("/plugin/save")
    public Response<String> savePluginCrawler(@RequestBody PluginCrawlerParam crawlerParam) {
        return crawlerService.savePluginCrawler(crawlerParam);
    }

}
