package com.qiyue.crawler.service;

import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.entity.ContentEntity;

public interface ContentService {
    Response<ContentEntity> findByContentId(Long contentId);
}
