package com.qiyue.crawler.service;

import com.qiyue.crawler.dao.entity.CategoryEntity;
import com.qiyue.crawler.dao.entity.NewsEntity;
import com.qiyue.crawler.dao.entity.WebEntity;
import com.qiyue.crawler.dao.repo.CategoryRepository;
import com.qiyue.crawler.dao.repo.NewsRepository;
import com.qiyue.crawler.dao.repo.WebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawlerService {

    @Autowired
    private WebRepository webRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewsRepository newsRepository;

    public List<WebEntity> findAllWebs(){
        return webRepository.findByState("0");
    }

    public List<CategoryEntity> findAllCategories(){
        return categoryRepository.findByState("0");
    }

    public List<NewsEntity> findByCategoryUrl(String categoryUrl){
        return newsRepository.findByCategoryUrl(categoryUrl);
    }
}
