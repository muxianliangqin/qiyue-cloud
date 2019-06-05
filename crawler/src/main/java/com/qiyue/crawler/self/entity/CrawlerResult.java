package com.qiyue.crawler.self.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CrawlerResult {
    private int userId;
    private String origin;
    private String baseURI;
    private String title;
    private String xpath;
    private ArrayList<NewsLink> validResults;
}
