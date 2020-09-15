package com.qiyue.crawler.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*网站栏目*/
@Entity
@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "`column`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"url", "xpath_article_title"}),
        @UniqueConstraint(columnNames = {"column_id"})
})
public class ColumnEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /*栏目ID*/
    @Column(name = "column_id")
    private Long columnId;
    /*栏目链接*/
    @Column(name = "url")
    private String url;
    /*栏目标题*/
    @Column(name = "title")
    private String title;
    /*抓取文章标题的xpath路径*/
    @Column(name = "xpath_article_title")
    private String xpathArticleTitle;
    /*抓取文章内容的xpath路径*/
    @Column(name = "xpath_article_content")
    private String xpathArticleContent;
    /*栏目归属网站的ID*/
    @Column(name = "web_id")
    private Long webId;
    /*网页编码*/
    @Column(name = "charset")
    private String charset;
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
