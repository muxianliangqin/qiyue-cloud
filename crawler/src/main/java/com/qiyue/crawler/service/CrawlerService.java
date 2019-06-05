package com.qiyue.crawler.service;

import com.qiyue.crawler.constant.Constant;
import com.qiyue.crawler.dao.entity.CategoryEntity;
import com.qiyue.crawler.dao.entity.NewsEntity;
import com.qiyue.crawler.dao.entity.WebEntity;
import com.qiyue.crawler.dao.repo.CategoryRepository;
import com.qiyue.crawler.dao.repo.NewsRepository;
import com.qiyue.crawler.dao.repo.WebRepository;
import com.qiyue.crawler.except.BaseExcept;
import com.qiyue.crawler.self.Response;
import com.qiyue.crawler.self.entity.CrawlerResult;
import com.qiyue.crawler.self.entity.NewsLink;
import com.qiyue.crawler.util.BaseUtil;
import com.qiyue.crawler.util.DateUtil;
import com.qiyue.crawler.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CrawlerService {

    @Autowired
    private WebRepository webRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewsRepository newsRepository;


    public Response findWebs(int userId,Pageable pageable){
        Page<WebEntity> webEntityPage = webRepository.findByUserIdAndState(userId,"0",pageable);
        return Response.success(webEntityPage);
    }

    public Response findByCategoryUrlAndState(int categoryId,Pageable pageable){
        Page<NewsEntity> newsEntityPage = newsRepository.findByCategoryIdAndState(categoryId,"0", pageable);
        return Response.success(newsEntityPage);
    }

    @Transactional
    public Response deleteWeb(int webId) {
        long num = webRepository.updateState(webId);
        num += categoryRepository.updateStateByWebId(webId);
        return Response.success(num);
    }

    @Transactional
    public Response addWeb(String title, String url, int userId) {
        long num = webRepository.add(title, url, userId);
        return Response.success(num);
    }

    @Transactional
    public Response modifyWeb(WebEntity webEntity){
        Optional<WebEntity> webEntityOptional = webRepository.findById(webEntity.getId());
        if (!webEntityOptional.isPresent()) {
            return Response.fail("NO_RECORD");
        }
        webEntity.setUpdateTime(DateUtil.getSystemTime(Constant.DATE_FORMATER_WITH_HYPHEN));
//        categoryEntity.setUpdateUser("");
        SqlUtil.copyNullProperties(webEntityOptional.get(),webEntity);
        WebEntity newOne = webRepository.saveAndFlush(webEntity);
        return Response.success(newOne);
    }

    @Transactional
    public Response deleteCategory(int categoryId) {
        long num = categoryRepository.updateState(categoryId);
        return Response.success(num);
    }

    @Transactional
    public Response addCategory(String title, String url, String xpath, int webId) {
        long num = categoryRepository.add(title, url, xpath, webId);
        return Response.success(num);
    }

    @Transactional
    public Response modifyCategory(CategoryEntity categoryEntity){
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(categoryEntity.getId());
        if (!categoryEntityOptional.isPresent()) {
            return Response.fail("NO_RECORD");
        }
        categoryEntity.setUpdateTime(DateUtil.getSystemTime(Constant.DATE_FORMATER_WITH_HYPHEN));
//        categoryEntity.setUpdateUser("");
        SqlUtil.copyNullProperties(categoryEntityOptional.get(),categoryEntity);
        CategoryEntity newOne = categoryRepository.saveAndFlush(categoryEntity);
        return Response.success(newOne);
    }

    @Transactional
    public Response pluginResultSave(CrawlerResult crawlerResult) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findByUrl(crawlerResult.getBaseURI());
        if (categoryEntityOptional.isPresent()) {
            saveNewsEntities(categoryEntityOptional.get().getId(), crawlerResult);
        } else {
            Optional<WebEntity> optionalWebEntity = webRepository.findByUrl(crawlerResult.getOrigin());
            if (optionalWebEntity.isPresent()) {
                CategoryEntity categoryEntity = saveCategoryEntities(optionalWebEntity.get().getId(),crawlerResult);
                List<NewsEntity> newsEntities = saveNewsEntities(categoryEntity.getId(),crawlerResult);
            } else {
                WebEntity webEntity = saveWebEntities(crawlerResult);
                CategoryEntity categoryEntity = saveCategoryEntities(webEntity.getId(),crawlerResult);
                List<NewsEntity> newsEntities = saveNewsEntities(categoryEntity.getId(),crawlerResult);
            }
        }
        return Response.success("ok");
    }

    private List<NewsEntity> saveNewsEntities(int categoryId, CrawlerResult crawlerResult) {
        List<NewsLink> newsLinks = crawlerResult.getValidResults();
        List<NewsEntity> newsEntities = newsLinks.stream().map((v)->{
            NewsEntity newsEntity = new NewsEntity();
            newsEntity.setUrl(v.getHref());
            newsEntity.setTitle(v.getTitle());
            newsEntity.setCategoryId(categoryId);
            newsEntity.setCreateTime(DateUtil.getSystemTime(Constant.DATE_FORMATER_WITH_HYPHEN));
            newsEntity.setUpdateTime(DateUtil.getSystemTime(Constant.DATE_FORMATER_WITH_HYPHEN));
            return newsEntity;
        }).collect(Collectors.toList());
        return newsRepository.saveAll(newsEntities);
    }

    private CategoryEntity saveCategoryEntities(int webId, CrawlerResult crawlerResult) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setUrl(crawlerResult.getBaseURI());
        categoryEntity.setTitle(crawlerResult.getTitle());
        categoryEntity.setXpath(crawlerResult.getXpath());
        categoryEntity.setWebId(webId);
        categoryEntity.setState("0");
        categoryEntity.setCreateTime(DateUtil.getSystemTime(Constant.DATE_FORMATER_WITH_HYPHEN));
        categoryEntity.setUpdateTime(DateUtil.getSystemTime(Constant.DATE_FORMATER_WITH_HYPHEN));
        return categoryRepository.save(categoryEntity);
    }

    private WebEntity saveWebEntities(CrawlerResult crawlerResult) {
        WebEntity webEntity = new WebEntity();
        webEntity.setUrl(crawlerResult.getOrigin());
        webEntity.setTitle("null");
        webEntity.setUserId(crawlerResult.getUserId());
        webEntity.setState("0");
        webEntity.setCreateTime(DateUtil.getSystemTime(Constant.DATE_FORMATER_WITH_HYPHEN));
        webEntity.setUpdateTime(DateUtil.getSystemTime(Constant.DATE_FORMATER_WITH_HYPHEN));
        return webRepository.save(webEntity);
    }


}
