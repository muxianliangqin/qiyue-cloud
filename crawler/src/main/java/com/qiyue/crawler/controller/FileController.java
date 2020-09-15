package com.qiyue.crawler.controller;

import com.qiyue.base.model.request.Request;
import com.qiyue.crawler.service.CrawlerService;
import com.qiyue.crawler.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("file")
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping("download")
    public void download(@RequestBody Request<Long> request, HttpServletResponse response){
        fileService.download(request.getParams(), response);
    }
}
