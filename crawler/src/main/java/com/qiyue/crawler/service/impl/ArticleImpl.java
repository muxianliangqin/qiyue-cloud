package com.qiyue.crawler.service.impl;

import com.qiyue.base.model.response.Response;
import com.qiyue.base.utils.ParamVerify;
import com.qiyue.base.utils.StringUtil;
import com.qiyue.crawler.dao.entity.ArticleDao;
import com.qiyue.crawler.dao.entity.ColumnDao;
import com.qiyue.crawler.entity.ArticleEntity;
import com.qiyue.crawler.entity.ColumnEntity;
import com.qiyue.crawler.enums.ArticleCrawlerAttachmentEnum;
import com.qiyue.crawler.enums.ArticleCrawlerContentEnum;
import com.qiyue.crawler.model.param.ArticleSpecificationParam;
import com.qiyue.crawler.service.ArticleService;
import com.qiyue.service.enums.DataStateEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ColumnDao columnDao;

    @SuppressWarnings("unchecked")
    @Override
    public Response<Page<ArticleEntity>> findBySpecification(ArticleSpecificationParam params, Pageable pageable) {
        Specification<ArticleEntity> specification = getSpecification(params);
        Page<ArticleEntity> articleEntityPage = articleDao.findAll(specification, pageable);
        return Response.success(articleEntityPage);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Response<List<ArticleEntity>> findBySpecification(ArticleSpecificationParam params) {
        Specification<ArticleEntity> specification = getSpecification(params);
        List<ArticleEntity> articleEntityPage = articleDao.findAll(specification);
        return Response.success(articleEntityPage);
    }

    @SuppressWarnings("unchecked")
    private Specification<ArticleEntity> getSpecification(ArticleSpecificationParam params) {
        return new Specification<ArticleEntity>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> andList = new ArrayList<>();
                // 标题模糊查询
                String title = params.getTitle();
                if (StringUtils.isNotEmpty(title)) {
                    Predicate predicate = criteriaBuilder.like(root.<String>get("title"), StringUtil.format("%{}%", title));
                    andList.add(predicate);
                }
                // 栏目精确查询
                Long columnId = params.getColumnId();
                if (null != columnId) {
                    Predicate predicate = criteriaBuilder.equal(root.get("columnId"), columnId);
                    andList.add(predicate);
                } else {
                    // 网站查询
                    Long webId = params.getWebId();
                    if (null != webId) {
                        List<ColumnEntity> columnEntityList = columnDao.findByWebIdAndState(webId, DataStateEnum.ORIGINAL.getState());
                        List<Long> columnIdList = columnEntityList.stream().map(ColumnEntity::getColumnId).collect(Collectors.toList());
                        andList.add(root.<Long>get("webId").in(columnIdList));
                    }
                }
                List<Predicate> orList = new ArrayList<>();
                // 是否爬取内容
                Integer crawledContent = params.getCrawledContent();
                if (null != crawledContent) {
                    ParamVerify.validity(crawledContent, ArticleCrawlerContentEnum.values(), ArticleCrawlerContentEnum::getStatus, "crawledContent");
                    Predicate predicate = criteriaBuilder.equal(root.<Integer>get("crawledContent"), crawledContent);
                    orList.add(predicate);
                }
                // 是否爬取附件
                Integer crawledAttachment = params.getCrawledAttachment();
                if (null != crawledAttachment) {
                    ParamVerify.validity(crawledAttachment, ArticleCrawlerAttachmentEnum.values(), ArticleCrawlerAttachmentEnum::getStatus, "crawledAttachment");
                    Predicate predicate = criteriaBuilder.equal(root.<Integer>get("crawledAttachment"), crawledAttachment);
                    orList.add(predicate);
                }
                if (orList.size() > 0) {
                    Predicate[] orPredicates = new Predicate[orList.size()];
                    Predicate predicate = criteriaBuilder.or(orPredicates);
                    andList.add(predicate);
                }
                // 数据是否有效
                Integer state = params.getState();
                if (null != state) {
                    ParamVerify.validity(state, DataStateEnum.values(), DataStateEnum::getState, "state");
                    Predicate predicate = criteriaBuilder.equal(root.<Integer>get("state"), state);
                    andList.add(predicate);
                }
                Integer crawledNum = params.getCrawledNum();
                if (null != crawledNum && crawledNum > 0) {
                    Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.<Integer>get("crawledNum"), crawledNum);
                    andList.add(predicate);
                }
                Predicate[] predicates = new Predicate[andList.size()];
                return criteriaBuilder.and(andList.toArray(predicates));
            }
        };
    }
}