package com.qiyue.crawler.service.impl;

import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.DatabaseException;
import com.qiyue.base.model.response.Response;
import com.qiyue.base.utils.ParamVerify;
import com.qiyue.crawler.dao.entity.ColumnDao;
import com.qiyue.crawler.entity.ColumnEntity;
import com.qiyue.crawler.service.ColumnService;
import com.qiyue.crawler.utils.IdUtil;
import com.qiyue.service.enums.DataStateEnum;
import com.qiyue.service.utils.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColumnImpl implements ColumnService {

    @Autowired
    private ColumnDao columnDao;

    @Override
    public Response<List<ColumnEntity>> findByWebId(Long webId) {
        List<ColumnEntity> columnEntityList = columnDao.findByWebIdAndState(webId, DataStateEnum.ORIGINAL.getState());
        return Response.success(columnEntityList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> add(ColumnEntity params) {
        ParamVerify.isNotNull(params.getUrl(), "栏目链接", "url");
        ParamVerify.isNotNull(params.getTitle(), "栏目标题", "title");
        ParamVerify.isNotNull(params.getWebId(), "栏目归属网站ID", "webId");
        if (columnDao.findByUrl(params.getUrl()).isPresent()) {
            throw new DatabaseException(ErrorEnum.RECORD_HAS_EXIST, "url", params.getUrl());
        }
        params.setColumnId(IdUtil.nextId());
        params.setCreateUser(ContextUtil.getUser().getUserId());
        columnDao.save(params);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> delete(Long columnId) {
        ParamVerify.isNotNull(columnId, "栏目ID", "columnId");
        ColumnEntity columnEntity = columnDao.findByColumnId(columnId)
                .orElseThrow(() -> new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "columnId", "栏目ID"));
        columnDao.delete(columnEntity);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> modify(ColumnEntity params) {
        ParamVerify.isNotNull(params.getColumnId(), "栏目ID", "columnId");
        ColumnEntity columnEntity = columnDao.findByColumnId(params.getColumnId())
                .orElseThrow(() -> new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "columnId", "栏目ID"));
        columnEntity.setUrl(params.getUrl());
        columnEntity.setTitle(params.getTitle());
        columnEntity.setXpathArticleTitle(params.getXpathArticleTitle());
        columnEntity.setXpathArticleContent(params.getXpathArticleContent());
        columnEntity.setCharset(params.getCharset());
        columnEntity.setUpdateUser(ContextUtil.getUser().getUserId());
        columnDao.save(columnEntity);
        return Response.success();
    }

}
