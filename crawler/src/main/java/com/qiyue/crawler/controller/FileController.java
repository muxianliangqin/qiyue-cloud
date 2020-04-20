package com.qiyue.crawler.controller;

import com.qiyue.crawler.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("file")
public class FileController {
    @Autowired
    private CrawlerService crawlerService;

    @RequestMapping("download")
    public void download(HttpServletResponse response, int id){
        crawlerService.download(response, id);
    }
}
