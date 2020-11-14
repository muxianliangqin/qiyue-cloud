package com.qiyue.crawler.controller;

import com.qiyue.base.model.request.Request;
import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.entity.ContentEntity;
import com.qiyue.crawler.service.ContentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @RequestMapping("/findByContentId")
    public Response<ContentEntity> findBySpecification(@RequestBody Request<Long> request) {
        return contentService.findByContentId(request.getParams());
    }

}
