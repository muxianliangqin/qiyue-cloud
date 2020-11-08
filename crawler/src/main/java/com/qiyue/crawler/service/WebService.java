package com.qiyue.crawler.service;

import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.entity.WebEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WebService {

    Response<Page<WebEntity>> findByPage(Pageable pageable);

    Response<List<WebEntity>> findByTitleLike(String title);

    Response<String> delete(Long webId);

    Response<String> add(WebEntity webEntity);

    Response<String> modify(WebEntity webEntity);

}
