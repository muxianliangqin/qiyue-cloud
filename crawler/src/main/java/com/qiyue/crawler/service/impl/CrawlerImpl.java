package com.qiyue.crawler.service.impl;

import com.alibaba.fastjson.util.IOUtils;
import com.qiyue.base.constant.Constant;
import com.qiyue.base.constant.ErrorConstant;
import com.qiyue.base.request.CrawlerResult;
import com.qiyue.base.request.NewsLink;
import com.qiyue.base.util.SqlUtil;
import com.qiyue.crawler.dao.em.CrawlerEntityManager;
import com.qiyue.crawler.dao.entity.AttachmentsEntity;
import com.qiyue.crawler.dao.entity.CategoryEntity;
import com.qiyue.crawler.dao.entity.NewsEntity;
import com.qiyue.crawler.dao.entity.WebEntity;
import com.qiyue.crawler.dao.repo.AttachmentsRepository;
import com.qiyue.crawler.dao.repo.CategoryRepository;
import com.qiyue.crawler.dao.repo.NewsRepository;
import com.qiyue.crawler.dao.repo.WebRepository;
import com.qiyue.crawler.service.CrawlerService;
import com.qiyue.service.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CrawlerImpl implements CrawlerService {

    @Autowired
    private WebRepository webRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CrawlerEntityManager crawlerEntityManager;
    @Autowired
    private AttachmentsRepository attachmentsRepository;

    @Override
    public Response findWebs(int userId, Pageable pageable) {
        Page<WebEntity> webEntityPage = webRepository.findByUserIdAndState(userId, "0", pageable);
        return Response.success(webEntityPage);
    }

    @Override
    public Response findByUserIdInAndState(List<Integer> userIds, Pageable pageable) {
        Page<WebEntity> webEntityPage = webRepository.findByUserIdInAndState(userIds, "0", pageable);
        return Response.success(webEntityPage);
    }

    @Override
    public Response findByCategoryIdAndState(int categoryId, Pageable pageable) {
        Page<NewsEntity> newsEntityPage = newsRepository.findByCategoryIdAndState(categoryId, "0", pageable);
        return Response.success(newsEntityPage);
    }

    @Override
    @Transactional
    public Response deleteWeb(int webId) {
        long num = webRepository.updateState(webId);
        num += categoryRepository.updateStateByWebId(webId);
        return Response.success(num);
    }

    @Override
    @Transactional
    public Response addWeb(String title, String url, int userId) {
        long num = webRepository.add(title, url, userId);
        return Response.success(num);
    }

    @Override
    @Transactional
    public Response modifyWeb(WebEntity webEntity) {
        Optional<WebEntity> webEntityOptional = webRepository.findById(webEntity.getId());
        if (!webEntityOptional.isPresent()) {
            return Response.fail(ErrorConstant.NO_RECORD);
        }
        webEntity.setUpdateTime(new Date());
//        categoryEntity.setUpdateUser("");
        // 使用此方法时，注意对象的int等属性使用Integer包装类，否则属性初始化值不是null，导致不会替换
        SqlUtil.copyNullProperties(webEntityOptional.get(), webEntity);
        WebEntity newOne = webRepository.saveAndFlush(webEntity);
        return Response.success(newOne);
    }

    @Override
    @Transactional
    public Response deleteCategory(int categoryId) {
        long num = categoryRepository.updateState(categoryId);
        return Response.success(num);
    }

    @Override
    @Transactional
    public Response addCategory(String title, String url, String xpathTitle, String xpathText, String charset, int webId) {
        long num = categoryRepository.add(title, url, xpathTitle, xpathText, charset, webId);
        return Response.success(num);
    }

    @Override
    @Transactional
    public Response modifyCategory(CategoryEntity categoryEntity) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(categoryEntity.getId());
        if (!categoryEntityOptional.isPresent()) {
            return Response.fail(ErrorConstant.NO_RECORD);
        }
        categoryEntity.setUpdateTime(new Date());
//        categoryEntity.setUpdateUser("");
        SqlUtil.copyNullProperties(categoryEntityOptional.get(), categoryEntity);
        CategoryEntity newOne = categoryRepository.saveAndFlush(categoryEntity);
        return Response.success(newOne);
    }

    @Override
    @Transactional
    public Response pluginResultSave(CrawlerResult crawlerResult) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findByUrl(crawlerResult.getBaseURI());
        if (categoryEntityOptional.isPresent()) {
            updateCategoryEntity(categoryEntityOptional.get(), crawlerResult);
            saveNewsEntities(categoryEntityOptional.get().getId(), crawlerResult);
        } else {
            Optional<WebEntity> optionalWebEntity = webRepository.findByUrl(crawlerResult.getOrigin());
            if (optionalWebEntity.isPresent()) {
                CategoryEntity categoryEntity = saveCategoryEntities(optionalWebEntity.get().getId(), crawlerResult);
                saveNewsEntities(categoryEntity.getId(), crawlerResult);
            } else {
                WebEntity webEntity = saveWebEntities(crawlerResult);
                CategoryEntity categoryEntity = saveCategoryEntities(webEntity.getId(), crawlerResult);
                saveNewsEntities(categoryEntity.getId(), crawlerResult);
            }
        }
        return Response.success("ok");
    }

    private void saveNewsEntities(int categoryId, CrawlerResult crawlerResult) {
        List<NewsLink> newsLinks = crawlerResult.getValidResults();
        List<NewsEntity> newsEntities = newsLinks.stream().map((v) -> {
            NewsEntity newsEntity = new NewsEntity();
            newsEntity.setUrl(v.getHref());
            newsEntity.setTitle(v.getTitle());
            newsEntity.setCategoryId(categoryId);
            return newsEntity;
        }).collect(Collectors.toList());
        crawlerEntityManager.newsBatchUpdate(newsEntities);
    }

    private CategoryEntity saveCategoryEntities(int webId, CrawlerResult crawlerResult) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setUrl(crawlerResult.getBaseURI());
        categoryEntity.setTitle(crawlerResult.getTitle());
        categoryEntity.setXpathTitle(crawlerResult.getXpathTitle());
        categoryEntity.setXpathText(crawlerResult.getXpathText());
        categoryEntity.setCharset(crawlerResult.getCharset());
        categoryEntity.setWebId(webId);
        categoryEntity.setState("0");
        categoryEntity.setNewNum(0);
        categoryEntity.setCreateTime(new Date());
        categoryEntity.setUpdateTime(new Date());
        return categoryRepository.save(categoryEntity);
    }

    private CategoryEntity updateCategoryEntity(CategoryEntity old, CrawlerResult crawlerResult) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setTitle(crawlerResult.getTitle());
        categoryEntity.setXpathTitle(crawlerResult.getXpathTitle());
        categoryEntity.setXpathText(crawlerResult.getXpathText());
        categoryEntity.setCharset(crawlerResult.getCharset());
        categoryEntity.setUpdateTime(new Date());
        SqlUtil.copyNullProperties(old, categoryEntity);
        CategoryEntity newOne = categoryRepository.saveAndFlush(categoryEntity);
        return newOne;
    }

    private WebEntity saveWebEntities(CrawlerResult crawlerResult) {
        WebEntity webEntity = new WebEntity();
        webEntity.setUrl(crawlerResult.getOrigin());
        webEntity.setTitle("null");
        webEntity.setUserId(crawlerResult.getUserId());
        webEntity.setState("0");
        return webRepository.save(webEntity);
    }

    @Override
    @Transactional
    public Response categoryHasRead(int categoryId) {
        int num = categoryRepository.hasRead(categoryId);
        return Response.success(num);
    }

    @Override
    @Transactional
    public Response newsHasRead(int newsId) {
        int num = newsRepository.hasRead(newsId);
        return Response.success(num);
    }

    @Override
    public void download(HttpServletResponse response, int id) {
        Optional<AttachmentsEntity> attachmentsEntityOptional = attachmentsRepository.findById(id);
        if (attachmentsEntityOptional.isPresent()) {
            AttachmentsEntity attachmentsEntity = attachmentsEntityOptional.get();
            String filePath = attachmentsEntity.getPath();
            FileInputStream fis = null;
            OutputStream os = null;
            try {
                fis = new FileInputStream(filePath);
                os = response.getOutputStream();
                byte[] bytes = new byte[Constant.IO_BYTES_BUFFER_SIZE];
                int index = -1;
                while ((index = fis.read(bytes)) != -1) {
                    os.write(bytes, 0, index);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.close(fis);
                IOUtils.close(os);
            }
        }
    }
}
