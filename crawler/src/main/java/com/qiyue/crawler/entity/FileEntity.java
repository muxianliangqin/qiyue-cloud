package com.qiyue.crawler.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@Data
@Table(name = "file")
public class FileEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /*文件ID*/
    @Column(name = "file_id")
    private Long fileId;
    /*存储路径*/
    @Column(name = "path")
    private String path;
    /*文件名称*/
    @Column(name = "name")
    private String name;
    /*文件格式*/
    @Column(name = "format")
    private String format;
    /*文件大小*/
    @Column(name = "size")
    private Integer size;
    /*文件的MD5摘要值*/
    @Column(name = "md5")
    private String md5;
    /*有效期，单位秒，默认0表示永不失效，超出有效期的文件可能会被清理*/
    @Column(name = "expires")
    private Integer expires;
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
