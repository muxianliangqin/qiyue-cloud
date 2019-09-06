package com.qiyue.common.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CrawlerResult {
    private int userId;
    private String origin;
    private String baseURI;
    private String title;
    private String xpathTitle;
    private String xpathText;
    private String charset;
    private String text;
    private ArrayList<NewsLink> validResults;
}
