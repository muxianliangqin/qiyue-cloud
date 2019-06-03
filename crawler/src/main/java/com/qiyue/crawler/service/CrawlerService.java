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
import com.qiyue.crawler.util.BaseUtil;
import com.qiyue.crawler.util.DateUtil;
import com.qiyue.crawler.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Response findByCategoryUrlAndState(String categoryUrl,Pageable pageable){
        Page<NewsEntity> newsEntityPage = newsRepository.findByCategoryUrlAndState(categoryUrl,"0", pageable);
        return Response.success(newsEntityPage);
    }

    @Transactional
    public Response deleteCategory(int categoryId) {
        long num = categoryRepository.updateState(categoryId);
        return Response.success(num);
    }

    @Transactional
    public Response addCategory(String title, String url, String xpath, String webUrl) {
        long num = categoryRepository.add(title, url, xpath, webUrl);
        return Response.success(num);
    }

    @Transactional
    public Response ModifyCategory(CategoryEntity categoryEntity){
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
}
