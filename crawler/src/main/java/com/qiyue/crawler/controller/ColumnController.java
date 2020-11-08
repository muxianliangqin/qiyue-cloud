package com.qiyue.crawler.controller;

import com.qiyue.base.model.request.Request;
import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.entity.ColumnEntity;
import com.qiyue.crawler.entity.WebEntity;
import com.qiyue.crawler.service.ColumnService;
import com.qiyue.crawler.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/column")
public class ColumnController {
    @Autowired
    private ColumnService columnService;

    @RequestMapping("/findByWebId")
    public Response<List<ColumnEntity>> findByWebId(@RequestBody Request<Long> request){
        return columnService.findByWebId(request.getParams());
    }

    @RequestMapping("/findByTitleLike")
    public Response<List<ColumnEntity>> findByTitleLike(@RequestBody Request<String> request){
        return columnService.findByTitleLike(request.getParams());
    }

    @RequestMapping("/delete")
    public Response<String> delete(@RequestBody Request<Long> request){
        return columnService.delete(request.getParams());
    }

    @RequestMapping("/add")
    public Response<String> add(@RequestBody Request<ColumnEntity> request){
        return columnService.add(request.getParams());
    }

    @RequestMapping("/modify")
    public Response<String> modify(@RequestBody Request<ColumnEntity> request){
        return columnService.modify(request.getParams());
    }
}
