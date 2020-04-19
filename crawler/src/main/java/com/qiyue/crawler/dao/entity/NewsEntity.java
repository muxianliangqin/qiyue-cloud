package com.qiyue.crawler.dao.entity;

import lombok.Data;
import org.hibernate.annotations.OrderBy;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "news", uniqueConstraints = {
        @UniqueConstraint(columnNames = "url")
})
public class NewsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "unread")
    private String unread;

    @Column(name = "text_state")
    private String textState;

    @Column(name = "match_result")
    private String matchResult;

    @Column(name = "attachment_state")
    private String attachmentState;

    @Column(name = "news_state")
    private String state;

    @Column(name = "create_time")
    private Date createTime = new Date();

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time")
    private Date updateTime = new Date();

    @Column(name = "update_user")
    private String updateUser;

    @OneToMany
    @JoinColumn(name = "news_id", referencedColumnName = "id")
    @OrderBy(clause = "create_time desc")
    private List<AttachmentsEntity> attachments;

    @OneToMany
    @JoinColumn(name = "news_id", referencedColumnName = "id")
    private List<TextsEntity> texts;
}
