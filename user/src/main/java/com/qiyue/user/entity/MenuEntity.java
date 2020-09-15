package com.qiyue.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "menu", uniqueConstraints = {
        @UniqueConstraint(columnNames = "menu_Id")
})
public class MenuEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "name")
    private String name;

    @Column(name = "`desc`")
    private String desc;

    @Column(name = "super_menu_id")
    private Long superMenuId;

    @Column(name = "component_name")
    private String componentName;

    @Column(name = "rank_no")
    private Integer rankNo;

    @Column(name = "sort_no")
    private Integer sortNo;

    /**
     * 类型，1-菜单，2-仅html页面，3-页面内鉴权组件，按钮、链接的
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 执行后台操作的类型，1-read,只读，2-add,新增,3-update,更新，4-delete删除
     */
    @Column(name = "operate_type")
    private Integer operateType;

    /**
     * 菜单图标
     */
    @Column(name = "icon")
    private String icon;

    @Column(name = "state")
    private Integer state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_user")
    private Long createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_user")
    private Long updateUser;


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
}
