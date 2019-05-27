package com.qiyue.crawler.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiyue.crawler.constant.Constant;
import com.qiyue.crawler.dao.entity.CategoryEntity;
import com.qiyue.crawler.dao.entity.NewsEntity;
import com.qiyue.crawler.dao.entity.WebEntity;
import com.qiyue.crawler.dao.repo.CategoryRepository;
import com.qiyue.crawler.dao.repo.NewsRepository;
import com.qiyue.crawler.dao.repo.WebRepository;
import com.qiyue.crawler.except.BaseExcept;
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


    public Page<WebEntity> findWebs(int userId,Pageable pageable){
        return webRepository.findByUserIdAndState(userId,"0",pageable);
    }

    public Page<NewsEntity> findByCategoryUrlAndState(String categoryUrl,Pageable pageable){
        return newsRepository.findByCategoryUrlAndState(categoryUrl,"0", pageable);
    }

    @Transactional
    public int deleteCategory(String categoryId) {
        return categoryRepository.updateState(Integer.parseInt(categoryId));
    }

    @Transactional
    public CategoryEntity ModifyCategory(CategoryEntity categoryEntity){
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(categoryEntity.getId());
        if (!categoryEntityOptional.isPresent()) {
            throw new BaseExcept("0000","更新记录不存在");
        }
//        categoryEntity.setUpdateUser("");
        SqlUtil.copyNullProperties(categoryEntityOptional.get(),categoryEntity);
        CategoryEntity newOne = categoryRepository.saveAndFlush(categoryEntity);
        return newOne;
    }
}
