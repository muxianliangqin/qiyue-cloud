package com.qiyue.user.dao.entity;

import com.qiyue.common.node.Element;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "menu", uniqueConstraints = {
        @UniqueConstraint(columnNames = "code")
})
public class MenuEntity extends Element implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "menu_desc")
    private String desc;

    @Column(name = "super_code")
    private String superCode;

    @Column(name = "menu_state")
    private String state;

    @Column(name = "create_time")
    private Date createTime = new Date();

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time")
    private Date updateTime = new Date();

    @Column(name = "update_user")
    private String updateUser;


    /*
    @OneToMany：表与表一对多关系
    @JoinColumn：
        表示两个表相关连的字段，name：many表的字段，referencedColumnName：one表的字段
    @Where：where条件子句，可用于静态条件
    @org.hibernate.annotations.OrderBy：order by子句
    @Filter：where子句，设置动态筛选条件，需要在many表上配置@FilterDef配合使用
    所有子句应填表的字段名称而不是实体类的属性名称
    生成sql语句：
    where menuloanen0_.loan_user_id = ?
    and ( menuloanen0_.state=0)
    and menuloanen0_.menu_code=?
    order by menuloanen0_.update_time desc
     */
    @OneToMany
    @JoinColumn(name = "menu_code", referencedColumnName = "code")
    @Where(clause = "state=0")
    @org.hibernate.annotations.OrderBy(clause = "update_time desc")
    @Filter(name = "userIdFilter", condition = "loan_user_id = :userId")
    private List<MenuLoanEntity> menuLoanEntities;

}
