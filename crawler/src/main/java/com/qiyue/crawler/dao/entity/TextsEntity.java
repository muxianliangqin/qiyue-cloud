package com.qiyue.crawler.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "texts", uniqueConstraints={
        @UniqueConstraint(columnNames = "news_id")
})
public class TextsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "news_id")
    private String newsId;

    @Column(name = "text")
    private String text;

    @Column(name = "create_time")
    private String createTime;

}
