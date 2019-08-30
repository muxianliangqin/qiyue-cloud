package com.qiyue.crawler.service;

import com.qiyue.crawler.dao.entity.KeywordEntity;
import com.qiyue.crawler.dao.entity.RegexpsEntity;
import com.qiyue.crawler.dao.repo.KeywordRepository;
import com.qiyue.crawler.dao.repo.RegexpsRepository;
import com.qiyue.crawler.self.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RegexpService {
    @Autowired
    private RegexpsRepository regexpsRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    public Response regexpFindAll(){
        Sort sort = new Sort(Sort.Direction.ASC, "code");
        List<RegexpsEntity> regexpsEntities = regexpsRepository.findAll(sort);
        return Response.success(regexpsEntities);
    }

    @Transactional
    public Response add(String name, String regexp, String codes, int userId) {
        int i = keywordRepository.add(name, regexp, codes, userId);
        return Response.success(i);
    }

    @Transactional
    public Response modify(String name, String regexp, String codes, int id) {
        int i = keywordRepository.modify(name, regexp, codes, id);
        return Response.success(i);
    }

    public Response keywordFindAll(int userId, Pageable pageable) {
        Page<KeywordEntity> keywordEntityPage = keywordRepository.findAllByUserId(userId, pageable);
        return Response.success(keywordEntityPage);
    }

    @Transactional
    public Response keywordDel(int id) {
        keywordRepository.deleteById(id);
        return Response.success("ok");
    }
}
