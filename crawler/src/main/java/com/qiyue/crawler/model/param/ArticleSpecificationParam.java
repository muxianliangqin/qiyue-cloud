package com.qiyue.crawler.model.param;

import lombok.Data;

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
    /*尝试爬取正文和附件的次数，默认超过5次就不在爬取*/
    private Integer crawledNum;
    /*状态，0-可用，1-删除*/
    private Integer state;
    /*文章归属网站ID*/
    private Long webId;
    /*文章ID*/
    private Long articleId;
    /*是否有正文*/
    private Integer haveText;
    /*是否有附件*/
    private Integer haveAttachment;

}
