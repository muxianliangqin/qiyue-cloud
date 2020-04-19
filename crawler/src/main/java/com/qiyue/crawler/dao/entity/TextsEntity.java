package com.qiyue.crawler.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "texts", uniqueConstraints = {
        @UniqueConstraint(columnNames = "news_id")
})
public class TextsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "news_id")
    private Integer newsId;

    @Column(name = "text")
    private String text;

    @Column(name = "create_time")
    private Date createTime = new Date();
}
