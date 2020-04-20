package com.qiyue.crawler.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qiyue.base.request.KeywordWebSet;
import com.qiyue.crawler.dao.em.CrawlerEntityManager;
import com.qiyue.crawler.dao.entity.KeywordEntity;
import com.qiyue.crawler.dao.entity.RegexpsEntity;
import com.qiyue.crawler.dao.repo.KeywordRepository;
import com.qiyue.crawler.dao.repo.RegexpsRepository;
import com.qiyue.crawler.service.RegexpService;
import com.qiyue.service.response.Response;
import com.qiyue.service.user.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RegexpImpl implements RegexpService {
    @Autowired
    private RegexpsRepository regexpsRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private CrawlerEntityManager crawlerEntityManager;

    @Autowired
    private UserHandler userHandler;

    @Override
    public Response regexpFindAll() {
        Sort sort = new Sort(Sort.Direction.ASC, "code");
        List<RegexpsEntity> regexpsEntities = regexpsRepository.findAll(sort);
        return Response.success(regexpsEntities);
    }

    @Override
    @Transactional
    public Response add(String name, String regexp, String codes) {
        int userId = userHandler.getUser().getId();
        int i = keywordRepository.add(name, regexp, codes, userId);
        return Response.success(i);
    }

    @Override
    @Transactional
    public Response modify(String name, String regexp, String codes, int id) {
        int i = keywordRepository.modify(name, regexp, codes, id);
        return Response.success(i);
    }

    @Override
    public Response keywordFindPage(Pageable pageable) {
        int userId = userHandler.getUser().getId();
        Page<KeywordEntity> keywordEntityPage = keywordRepository.findAllByUserId(userId, pageable);
        return Response.success(keywordEntityPage);
    }

    @Override
    public Response keywordFindAll() {
        int userId = userHandler.getUser().getId();
        List<KeywordEntity> keywordEntity = keywordRepository.findAllByUserId(userId);
        return Response.success(keywordEntity);
    }

    @Override
    public Response keywordWebSet(String request) {
        KeywordWebSet keywordWebSet = JSONObject.parseObject(request, KeywordWebSet.class);
        crawlerEntityManager.keywordWebSet(keywordWebSet.getWebId(), keywordWebSet.getCategories(),
                keywordWebSet.getKeywords());
        return Response.success();
    }

    @Override
    @Transactional
    public Response keywordDel(int id) {
        keywordRepository.deleteById(id);
        return Response.success("ok");
    }
}
