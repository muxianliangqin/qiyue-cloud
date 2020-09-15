package com.qiyue.crawler.service.impl;

import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.DatabaseException;
import com.qiyue.base.model.response.Response;
import com.qiyue.base.utils.ParamVerify;
import com.qiyue.crawler.dao.entity.ColumnDao;
import com.qiyue.crawler.dao.entity.WebDao;
import com.qiyue.crawler.entity.WebEntity;
import com.qiyue.crawler.service.WebService;
import com.qiyue.crawler.utils.IdUtil;
import com.qiyue.service.enums.DataStateEnum;
import com.qiyue.service.utils.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WebImpl implements WebService {

    @Autowired
    private WebDao webDao;

    @Autowired
    private ColumnDao columnDao;

    @Override
    public Response<Page<WebEntity>> findByPage(Pageable pageable) {
        Page<WebEntity> webEntityPage = webDao.findAllByState(DataStateEnum.USABLE.getState(), pageable);
        return Response.success(webEntityPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> add(WebEntity params) {
        ParamVerify.isNotNull(params.getUrl(), "网站链接", "url");
        ParamVerify.isNotNull(params.getTitle(), "网站标题", "title");
        if (webDao.findByUrl(params.getUrl()).isPresent()) {
            throw new DatabaseException(ErrorEnum.RECORD_HAS_EXIST, "url", params.getUrl());
        }
        params.setWebId(IdUtil.nextId());
        params.setCreateUser(ContextUtil.getUser().getUserId());
        webDao.save(params);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> delete(Long webId) {
        ParamVerify.isNotNull(webId, "网站链接", "url");
        WebEntity webEntity = webDao.findByWebId(webId)
                .orElseThrow(() -> new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "webId", "网站ID"));
        webDao.delete(webEntity);
        columnDao.deleteByWebId(webEntity.getWebId());
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> modify(WebEntity params) {
        ParamVerify.isNotNull(params.getWebId(), "网站链接", "url");
        WebEntity webEntity = webDao.findByWebId(params.getWebId())
                .orElseThrow(() -> new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "webId", "网站ID"));
        webEntity.setUrl(params.getUrl());
        webEntity.setTitle(params.getTitle());
        webEntity.setUpdateUser(ContextUtil.getUser().getUserId());
        webDao.save(webEntity);
        return Response.success();
    }

}
