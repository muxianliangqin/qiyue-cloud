package com.qiyue.crawler.service;

import com.qiyue.service.response.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface RegexpService {
    Response regexpFindAll();

    Response add(String name, String regexp, String codes);

    Response modify(String name, String regexp, String codes, int id);

    Response keywordFindPage(Pageable pageable);

    Response keywordFindAll();

    Response keywordWebSet(String request);

    Response keywordDel(int id);
}
