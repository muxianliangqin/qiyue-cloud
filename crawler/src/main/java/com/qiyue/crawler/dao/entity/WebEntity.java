package com.qiyue.crawler.dao.entity;

import lombok.Data;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "web", uniqueConstraints={
        @UniqueConstraint(columnNames = {"url"})
})
public class WebEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "web_state")
    private String state;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time")
    private String updateTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "user_id")
    private int userId;

    @OneToMany
    @JoinColumn(name = "web_id",referencedColumnName = "id")
    @Where(clause = "category_state=0")
    @OrderBy(clause = "update_time desc")
    private List<CategoryEntity> categories;

}
