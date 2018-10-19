package com.qiyue.crawler.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiyue.crawler.dao.entity.CategoryEntity;
import com.qiyue.crawler.dao.entity.NewsEntity;
import com.qiyue.crawler.dao.entity.WebEntity;
import com.qiyue.crawler.dao.repo.CategoryRepository;
import com.qiyue.crawler.dao.repo.NewsRepository;
import com.qiyue.crawler.dao.repo.WebRepository;
import com.qiyue.crawler.node.Menu;
import com.qiyue.crawler.node.Node;
import com.qiyue.crawler.node.NodeTree;
import com.qiyue.crawler.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String getMenuNode(){
        Node node = NodeTree.getInstance();
        List<WebEntity> webEntities = webRepository.findByState("0");
        Map<String,WebEntity> webEntityMap = new HashMap<>();
        webEntities.forEach(k->{webEntityMap.put(k.getUrl(),k);});
        List<CategoryEntity> categoryEntityMap = categoryRepository.findByState("0");
        webEntities.forEach(k->{
            Menu menu = new Menu();
            menu.setId("web_" + k.getId());
            menu.setName(k.getName());
            menu.setUrl(k.getUrl());
            menu.setDesc(k.getDesc());
            NodeTree.insert(node,menu);
        });
        categoryEntityMap.forEach(k->{
            if (webEntityMap.containsKey(k.getWebUrl())){
                Menu menu = new Menu();
                menu.setId(String.valueOf(k.getId()));
                menu.setName(k.getName());
                menu.setUrl(k.getUrl());
                menu.setSupId("web_" + webEntityMap.get(k.getWebUrl()).getId());
                menu.setDesc(k.getDesc());
                menu.setXpath(k.getXpath());
                NodeTree.insert(node,menu);
            }
        });
        JSONObject json = JSON.parseObject(node.toString());
        return json.toString();
    }

    public List<NewsEntity> findByCategoryUrl(String categoryUrl){
        return newsRepository.findByCategoryUrlAndState(categoryUrl,"0");
    }

    public long countTotalNum(String categoryUrl){
        return newsRepository.countByCategoryUrlAndState(categoryUrl,"0");
    }

    @Transactional
    public int deleteCategory(String categoryId) {
        return categoryRepository.updateState(Integer.parseInt(categoryId));
    }

    @Transactional
    public void ModifyCategory(Menu menu){
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(Integer.parseInt(menu.getId()));
        if (categoryEntityOptional.isPresent()) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(Integer.parseInt(menu.getId()));
            categoryEntity.setUrl(menu.getUrl());
            categoryEntity.setName(menu.getName());
            categoryEntity.setXpath(menu.getXpath());
            SqlUtil.copyNullProperties(categoryEntityOptional.get(),categoryEntity);
            CategoryEntity newOne = categoryRepository.saveAndFlush(categoryEntity);
        }
    }
}
