package com.qiyue.crawler.controller;

import com.qiyue.base.model.request.Request;
import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.entity.ArticleEntity;
import com.qiyue.crawler.entity.WebEntity;
import com.qiyue.crawler.model.param.ArticleSpecificationParam;
import com.qiyue.crawler.service.ArticleService;
import com.qiyue.crawler.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/findBySpecification")
    public Response<Page<ArticleEntity>> findBySpecification(@RequestBody Request<ArticleSpecificationParam> request){
        return articleService.findBySpecification(request.getParams(), request.getPage());
    }

}
