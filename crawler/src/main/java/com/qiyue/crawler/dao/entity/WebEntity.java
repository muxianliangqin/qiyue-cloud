package com.qiyue.crawler.dao.entity;

import lombok.Data;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"url"})
})
public class WebEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "web_state")
    private String state;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "create_time")
    private Date createTime = new Date();

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time")
    private Date updateTime = new Date();

    @Column(name = "update_user")
    private String updateUser;

    @OneToMany
    @JoinColumn(name = "web_id", referencedColumnName = "id")
    @Where(clause = "category_state=0")
    @OrderBy(clause = "update_time desc")
    private List<CategoryEntity> categories;

}
