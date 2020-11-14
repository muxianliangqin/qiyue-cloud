package com.qiyue.crawler.service.impl;

import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.dao.entity.ContentDao;
import com.qiyue.crawler.entity.ContentEntity;
import com.qiyue.crawler.service.ContentService;
import org.springframework.stereotype.Service;

@Service
public class ContentImpl implements ContentService {
    private final ContentDao contentDao;

    public ContentImpl(ContentDao contentDao) {
        this.contentDao = contentDao;
    }

    @Override
    public Response<ContentEntity> findByContentId(Long contentId) {
        return Response.success(contentDao.findByContentId(contentId));
    }
}
