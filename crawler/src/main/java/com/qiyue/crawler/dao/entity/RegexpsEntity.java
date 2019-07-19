package com.qiyue.crawler.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "regexps", uniqueConstraints={
        @UniqueConstraint(columnNames = "reg_exp")
})
public class RegexpsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "reg_exp")
    private String regexp;

    @Column(name = "reg_desc")
    private String desc;

    @Column(name = "classify")
    private String classify;

    @Column(name = "cls_desc")
    private String clsDesc;

    @Column(name = "super_cls")
    private String superCls;

}
