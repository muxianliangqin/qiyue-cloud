package com.qiyue.crawler.service;

import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.model.param.PluginCrawlerParam;

import javax.servlet.http.HttpServletResponse;

public interface CrawlerService {

    Response<String> savePluginCrawler(PluginCrawlerParam param);

}
