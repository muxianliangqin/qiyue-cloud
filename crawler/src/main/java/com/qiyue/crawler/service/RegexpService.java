package com.qiyue.crawler.service;

import com.qiyue.crawler.dao.entity.RegexpsEntity;
import com.qiyue.crawler.dao.repo.RegexpsRepository;
import com.qiyue.crawler.self.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegexpService {
    @Autowired
    private RegexpsRepository regexpsRepository;

    public Response regexpFindAll(){
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        List<RegexpsEntity> regexpsEntities = regexpsRepository.findAll(sort);
        return Response.success(regexpsEntities);
    }
}
