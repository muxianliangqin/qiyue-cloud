package com.qiyue.crawler.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*文章表*/
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "article", uniqueConstraints = {
        @UniqueConstraint(columnNames = "url")
})
public class ArticleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /*文章ID*/
    @Column(name = "article_id")
    private Long articleId;
    /*文章链接*/
    @Column(name = "url")
    private String url;
    /*文章标题*/
    @Column(name = "title")
    private String title;
    /*文章归属栏目ID*/
    @Column(name = "column_id")
    private Long columnId;
    /*是否已经爬取正文,0-未成功，1-已成功*/
    @Column(name = "crawled_text")
    private Integer crawledText;
    /*是否获取了html,0-未成功，1-已成功*/
    @Column(name = "crawled_html")
    private Integer crawledHtml;
    /*是否已爬取文章附件，0-未成功，1-已成功*/
    @Column(name = "crawled_attachment")
    private Integer crawledAttachment;
    /*尝试爬取正文和附件的次数，默认超过5次就不在爬取*/
    @Column(name = "crawled_num")
    private Integer crawledNum;
    /*内容*/
    @Column(name = "content_id")
    private String contentId;
    /*文章的附件，file_id的列表*/
    @Column(name = "attachments")
    private String attachments;
    /*爬取文章正文和附件时错误信息*/
    @Column(name = "error")
    private String error;
    /*状态，0-可用，1-删除*/
    @Column(name = "state")
    private Integer state;
    /*创建时间*/
    @Column(name = "create_time")
    private Date createTime;
    /*创建人*/
    @Column(name = "create_user")
    private Long createUser;
    /*更新时间*/
    @Column(name = "update_time")
    private Date updateTime;
    /*更新人*/
    @Column(name = "update_user")
    private Long updateUser;

}
