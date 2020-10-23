package com.qiyue.crawler.service;

import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.entity.ArticleEntity;
import com.qiyue.crawler.model.param.ArticleSpecificationParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    Response<Page<ArticleEntity>> findBySpecification(ArticleSpecificationParam params, Pageable pageable);

    Response<List<ArticleEntity>> findBySpecification(ArticleSpecificationParam params);

}
