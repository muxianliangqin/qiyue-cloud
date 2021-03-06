package com.qiyue.crawler.schedule;

import com.alibaba.fastjson.JSONObject;
import com.qiyue.base.model.request.Request;
import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.dao.entity.ColumnDao;
import com.qiyue.crawler.entity.ArticleEntity;
import com.qiyue.crawler.entity.ColumnEntity;
import com.qiyue.crawler.enums.ArticleCrawlerAttachmentEnum;
import com.qiyue.crawler.enums.ArticleCrawlerTextEnum;
import com.qiyue.crawler.model.param.ArticleSpecificationParam;
import com.qiyue.crawler.service.ArticleService;
import com.qiyue.service.enums.DataStateEnum;
import com.qiyue.service.kafka.Message;
import com.qiyue.service.kafka.Producer;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class CrawlerJob {

    @Resource
    private Producer producer;

    @Resource
    private ColumnDao columnDao;

    @Resource
    private ArticleService articleService;

    @Value(value = "${xxl-job.pushArticleInfoToKafka.pageSize}")
    private Integer pageSize;

    @XxlJob(value = "pushTitleInfoToKafka")
    public ReturnT<String> pushTitleInfoToKafka(String param) {
        List<ColumnEntity> columnEntityList = columnDao.findByStateOrderByCrawlTimeAsc(DataStateEnum.ORIGINAL.getState());
        columnEntityList.forEach(k -> {
            Message<ColumnEntity> message = new Message<>();
            message.setData(k);
            message.setLabel("crawlTitle");
            message.setVersion(new Date().getTime());
            producer.send(message);
        });
        log.info("pushTitleInfoToKafka，推送网站标题爬虫设置信息到kafka");
        return ReturnT.SUCCESS;
    }

    @XxlJob(value = "pushArticleInfoToKafka")
    public ReturnT<String> pushArticleInfoToKafka(String param) {
        Request<ArticleSpecificationParam> request = new Request<>();
        ArticleSpecificationParam params;
        if (StringUtils.isNotBlank(param)) {
            params = JSONObject.parseObject(param, ArticleSpecificationParam.class);
        } else {
            params = new ArticleSpecificationParam();
            params.setCrawledAttachment(ArticleCrawlerAttachmentEnum.NO.getStatus());
            params.setCrawledText(ArticleCrawlerTextEnum.NO.getStatus());
            params.setState(DataStateEnum.ORIGINAL.getState());
            params.setCrawledNum(5);
        }
        request.setParams(params);
        Request.Page page = new Request.Page();
        page.setSize(pageSize);
        page.setPage(0);
        List<String> orders = new ArrayList<>();
        orders.add("updateTime,ASC");
        page.setOrders(orders);
        request.setPage(page);
        log.info("执行定时任务-->pushArticleInfoToKafka，参数：{}", request);
        Response<Page<ArticleEntity>> response = articleService.findBySpecification(request.getParams(), request.getPage());
        response.getContent().forEach(k -> {
            k.setAttachments(null);
            k.setError(null);
            Message<ArticleEntity> message = new Message<>();
            message.setData(k);
            message.setLabel("crawlArticle");
            message.setVersion(new Date().getTime());
            producer.send(message);
        });
        log.info("pushArticleInfoToKafka，推送网站内容爬虫设置信息到kafka");
        return ReturnT.SUCCESS;
    }
}
