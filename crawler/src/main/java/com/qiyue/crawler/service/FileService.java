package com.qiyue.crawler.service;

import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.model.param.PluginCrawlerParam;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    // 附件下载
    void download(Long fileId, HttpServletResponse response);
}
