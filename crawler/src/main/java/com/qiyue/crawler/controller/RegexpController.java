package com.qiyue.crawler.controller;

import com.qiyue.crawler.dao.entity.RegexpsEntity;
import com.qiyue.crawler.self.Response;
import com.qiyue.crawler.service.RegexpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegexpController {
    @Autowired
    private RegexpService regexpService;

    @RequestMapping("/regexp/findAll")
    public Response regexpFindAll(){
        return regexpService.regexpFindAll();
    }

}
