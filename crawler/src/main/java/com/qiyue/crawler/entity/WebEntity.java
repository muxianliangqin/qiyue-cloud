package com.qiyue.crawler.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"web_id"})
})
public class WebEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /*网站ID*/
    @Column(name = "web_id")
    private Long webId;
    /*网站链接*/
    @Column(name = "url")
    private String url;
    /*网站标题*/
    @Column(name = "title")
    private String title;
    /*0-可用，1-不可用*/
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

//    @OneToMany
//    @JoinColumn(name = "web_id", referencedColumnName = "id")
//    @Where(clause = "category_state=0")
//    @OrderBy(clause = "update_time desc")
//    private List<ColumnEntity> categories;

}
