package com.qiyue.crawler.controller;

import com.qiyue.common.response.Response;
import com.qiyue.crawler.service.RegexpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegexpController {
    @Autowired
    private RegexpService regexpService;

    @RequestMapping("/regexp/regexpFindAll")
    public Response regexpFindAll() {
        return regexpService.regexpFindAll();
    }

    @RequestMapping("/regexp/keywordAdd")
    public Response add(String name, String regexp, String codes) {
        return regexpService.add(name, regexp, codes);
    }

    @RequestMapping("/regexp/keywordModify")
    public Response modify(String name, String regexp, String codes, int id) {
        return regexpService.modify(name, regexp, codes, id);
    }

    @RequestMapping("/regexp/keywordFindAll")
    public Response keywordFindAll(@PageableDefault(sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return regexpService.keywordFindAll(pageable);
    }

    @RequestMapping("/regexp/keywordDel")
    public Response keywordDel(int id) {
        return regexpService.keywordDel(id);
    }
}
