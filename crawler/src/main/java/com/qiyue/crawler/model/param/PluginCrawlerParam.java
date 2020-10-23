package com.qiyue.crawler.model.param;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PluginCrawlerParam {
    // 网站url
    private String webUrl;
    //    栏目url
    private String columnUrl;
    //    栏目标题
    private String columnTitle;
    //    获取文章标题xpath
    private String xpathArticleTitle;
    //    获取文章内容xpath
    private String xpathArticleContent;
    //    获取标题分页xpath
    private String xpathArticlePage;
    //    编码
    private String charset;
    //    文章列表
    private List<Article> articleList = new ArrayList<>();

    @Data
    public static class Article {
        private String url;
        private String title;
    }
}
