package com.qiyue.crawler.service.impl;

import com.alibaba.fastjson.util.IOUtils;
import com.qiyue.base.constant.Constant;
import com.qiyue.base.model.response.Response;
import com.qiyue.base.utils.ParamVerify;
import com.qiyue.crawler.dao.entity.ArticleDao;
import com.qiyue.crawler.dao.entity.ColumnDao;
import com.qiyue.crawler.dao.entity.FileDao;
import com.qiyue.crawler.dao.entity.WebDao;
import com.qiyue.crawler.entity.ArticleEntity;
import com.qiyue.crawler.entity.ColumnEntity;
import com.qiyue.crawler.entity.FileEntity;
import com.qiyue.crawler.entity.WebEntity;
import com.qiyue.crawler.model.param.PluginCrawlerParam;
import com.qiyue.crawler.service.CrawlerService;
import com.qiyue.crawler.utils.IdUtil;
import com.qiyue.service.utils.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@Service
public class CrawlerImpl implements CrawlerService {

    @Value(value = "${file.attachment.crawler.dir}")
    private String attachmentDir;

    @Autowired
    private WebDao webDao;
    @Autowired
    private ColumnDao columnDao;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private FileDao fileDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response savePluginCrawler(PluginCrawlerParam param) {
        ParamVerify.isNotNull(param.getWebUrl(), "网站链接", "webUrl");
        ParamVerify.isNotNull(param.getColumnUrl(), "栏目链接", "columnUrl");
        ParamVerify.isNotNull(param.getColumnTitle(), "栏目标题", "columnTitle");
        WebEntity webEntity = webDao.findByUrl(param.getWebUrl()).orElseGet(() -> {
            WebEntity newOne = new WebEntity();
            newOne.setUrl(param.getWebUrl());
            newOne.setWebId(IdUtil.nextId());
            newOne.setCreateUser(ContextUtil.getUser().getUserId());
            return webDao.save(newOne);
        });
        ColumnEntity columnEntity = columnDao.findByUrl(param.getColumnUrl()).orElseGet(() -> {
            ColumnEntity newOne = new ColumnEntity();
            newOne.setColumnId(IdUtil.nextId());
            newOne.setCreateUser(ContextUtil.getUser().getUserId());
            newOne.setWebId(webEntity.getWebId());
            newOne.setUrl(param.getColumnUrl());
            newOne.setTitle(param.getColumnTitle());
            newOne.setXpathArticleTitle(param.getXpathArticleTitle());
            newOne.setXpathArticleContent(param.getXpathArticleContent());
            newOne.setCharset(StringUtils.isEmpty(param.getCharset()) ? Constant.ENCODE_UTF8 : param.getCharset());
            return columnDao.save(newOne);
        });
        param.getArticleList().forEach(k -> {
            if (!articleDao.findByUrl(k.getUrl()).isPresent()) {
                ArticleEntity articleEntity = new ArticleEntity();
                articleEntity.setArticleId(IdUtil.nextId());
                articleEntity.setCreateUser(ContextUtil.getUser().getUserId());
                articleEntity.setColumnId(columnEntity.getColumnId());
                articleEntity.setUrl(k.getUrl());
                articleEntity.setTitle(k.getTitle());
                articleDao.save(articleEntity);
            }
        });
        return Response.success();
    }

}
