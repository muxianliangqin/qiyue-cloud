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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleImpl implements ArticleService {

    private final ArticleDao articleDao;
    private final ColumnDao columnDao;

    public ArticleImpl(ArticleDao articleDao, ColumnDao columnDao) {
        this.articleDao = articleDao;
        this.columnDao = columnDao;
    }

    @Override
    public Response<Page<ArticleEntity>> findBySpecification(ArticleSpecificationParam params, Pageable pageable) {
        Specification<ArticleEntity> specification = getSpecification(params);
        Page<ArticleEntity> articleEntityPage = articleDao.findAll(specification, pageable);
        return Response.success(articleEntityPage);
    }

    @Override
    public Response<List<ArticleEntity>> findBySpecification(ArticleSpecificationParam params) {
        Specification<ArticleEntity> specification = getSpecification(params);
        List<ArticleEntity> articleEntityPage = articleDao.findAll(specification);
        return Response.success(articleEntityPage);
    }

    private Specification<ArticleEntity> getSpecification(ArticleSpecificationParam params) {
        return (Specification<ArticleEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> andList = new ArrayList<>();
            // 标题模糊查询
            String title = params.getTitle();
            if (StringUtils.isNotEmpty(title)) {
                Predicate predicate = criteriaBuilder.like(root.get("title"), StringUtil.format("%{}%", title));
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
                    if (!CollectionUtils.isEmpty(columnIdList)) {
                        andList.add(root.<Long>get("columnId").in(columnIdList));
                    }
                }
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
                Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("crawledNum"), crawledNum);
                andList.add(predicate);
            }
            Predicate predicateAnd = criteriaBuilder.and(andList.toArray(new Predicate[0]));
            // or条件
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
            Predicate predicateOr = criteriaBuilder.or(orList.toArray(new Predicate[0]));
            List<Predicate> predicateWhere = new ArrayList<>();
            predicateWhere.add(predicateAnd);
            if (!CollectionUtils.isEmpty(orList)) {
                predicateWhere.add(predicateOr);
            }
            return query.where(predicateWhere.toArray(new Predicate[0])).getRestriction();
        };
    }
}
