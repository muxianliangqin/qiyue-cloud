package com.qiyue.crawler.service.impl;

import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.DatabaseException;
import com.qiyue.base.model.response.Response;
import com.qiyue.base.utils.ParamVerify;
import com.qiyue.base.utils.SqlUtil;
import com.qiyue.crawler.dao.entity.ColumnDao;
import com.qiyue.crawler.dao.entity.WebDao;
import com.qiyue.crawler.entity.ArticleEntity;
import com.qiyue.crawler.entity.ColumnEntity;
import com.qiyue.crawler.entity.WebEntity;
import com.qiyue.crawler.enums.ArticleCrawlerAttachmentEnum;
import com.qiyue.crawler.enums.ArticleCrawlerContentEnum;
import com.qiyue.crawler.model.param.ArticleSpecificationParam;
import com.qiyue.crawler.model.param.WebSpecificationParam;
import com.qiyue.crawler.service.WebService;
import com.qiyue.crawler.utils.IdUtil;
import com.qiyue.service.enums.DataStateEnum;
import com.qiyue.service.utils.ContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebImpl implements WebService {

    @Autowired
    private WebDao webDao;

    @Autowired
    private ColumnDao columnDao;

    @Override
    public Response<Page<WebEntity>> findByPage(WebSpecificationParam param, Pageable pageable) {
        Page<WebEntity> webEntityPage = webDao.findAll(this.getSpecification(param), pageable);
        return Response.success(webEntityPage);
    }

    @Override
    public Response<List<WebEntity>> findByTitleLike(String title) {
        return Response.success(webDao.findByTitleLike(SqlUtil.like(title)));
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

    private Specification<WebEntity> getSpecification(WebSpecificationParam params) {
        return (Specification<WebEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> andList = new ArrayList<>();
            // 标题模糊查询
            Long webId = params.getWebId();
            if (null != webId) {
                Predicate predicate = criteriaBuilder.equal(root.get("webId"), webId);
                andList.add(predicate);
            }
            Predicate predicateAnd = criteriaBuilder.and(andList.toArray(new Predicate[0]));
            List<Predicate> predicateWhere = new ArrayList<>();
            predicateWhere.add(predicateAnd);
            return query.where(predicateWhere.toArray(new Predicate[0])).getRestriction();
        };
    }
}
