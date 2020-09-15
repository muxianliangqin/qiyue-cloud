package com.qiyue.crawler.model.param;

import lombok.Data;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleSpecificationParam {
    /*文章标题*/
    private String title;
    /*文章归属栏目ID*/
    private Long columnId;
    /*是否已经爬取正文,0-未成功，1-已成功*/
    private Integer crawledContent;
    /*是否已爬取文章附件，0-未成功，1-已成功*/
    private Integer crawledAttachment;
    /*文章归属网站ID*/
    private Long webId;

}
