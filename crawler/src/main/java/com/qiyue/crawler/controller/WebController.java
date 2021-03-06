package com.qiyue.crawler.controller;

import com.qiyue.base.model.request.Request;
import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.entity.WebEntity;
import com.qiyue.crawler.model.param.WebSpecificationParam;
import com.qiyue.crawler.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/web")
public class WebController {
    @Autowired
    private WebService webService;

    @RequestMapping("/findByPage")
    public Response<Page<WebEntity>> findByPage(@RequestBody Request<WebSpecificationParam> request) {
        return webService.findByPage(request.getParams(), request.getPage());
    }

    @RequestMapping("/findByTitleLike")
    public Response<List<WebEntity>> findByTitleLike(@RequestBody Request<String> request) {
        return webService.findByTitleLike(request.getParams());
    }

    @RequestMapping("/delete")
    public Response<String> delete(@RequestBody Request<Long> request) {
        return webService.delete(request.getParams());
    }

    @RequestMapping("/add")
    public Response<String> add(@RequestBody Request<WebEntity> request) {
        return webService.add(request.getParams());
    }

    @RequestMapping("/modify")
    public Response<String> modify(@RequestBody Request<WebEntity> request) {
        return webService.modify(request.getParams());
    }
}
