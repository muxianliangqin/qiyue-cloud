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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            menu.setCode("web_" + k.getId());
            menu.setName(k.getName());
            menu.setUrl(k.getUrl());
            //menu.setSupCode("n002");
            menu.setDesc(k.getDesc());
            NodeTree.insert(node,menu);
        });
        categoryEntityMap.forEach(k->{
            if (webEntityMap.containsKey(k.getWebUrl())){
                Menu menu = new Menu();
                menu.setCode("category_" + k.getId());
                menu.setName(k.getName());
                menu.setUrl(k.getUrl());
                menu.setSupCode("web_" + webEntityMap.get(k.getWebUrl()).getId());
                menu.setDesc(k.getDesc());
                NodeTree.insert(node,menu);
            }
        });
        JSONObject json = JSON.parseObject(node.toString());
        return json.toString();
    }

    public List<NewsEntity> findByCategoryUrl(String categoryUrl){
        return newsRepository.findByCategoryUrl(categoryUrl);
    }
}
