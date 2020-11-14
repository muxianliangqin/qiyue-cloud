package com.qiyue.crawler.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*文章内容表*/
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "content", uniqueConstraints = {
        @UniqueConstraint(columnNames = "content_id")
})
public class ContentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /*文件内容ID*/
    @Column(name = "content_id")
    private Long contentId;
    /*正文文本*/
    @Column(name = "text")
    private String text;
    /*正文html*/
    @Column(name = "html")
    private String title;
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
