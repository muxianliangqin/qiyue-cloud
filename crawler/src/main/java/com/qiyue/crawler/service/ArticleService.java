package com.qiyue.crawler.service;

import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.entity.ArticleEntity;
import com.qiyue.crawler.model.param.ArticleSpecificationParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {

    Response<Page<ArticleEntity>> findBySpecification(ArticleSpecificationParam articleEntity, Pageable pageable);

}
