package com.qiyue.crawler.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "attachments")
public class AttachmentsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "news_id")
    private Integer newsId;

    @Column(name = "path")
    private String path;

    @Column(name = "name")
    private String name;

    @Column(name = "format")
    private String format;

    @Column(name = "create_time")
    private Date createTime = new Date();
}
