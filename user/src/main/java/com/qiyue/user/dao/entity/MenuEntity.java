package com.qiyue.user.dao.entity;

import com.qiyue.user.node.Element;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "menu",uniqueConstraints = {
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
    private String createTime;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time")
    private String updateTime;

    @Column(name = "update_user")
    private String updateUser;

    @OneToMany
    @JoinColumn(name = "menu_code",referencedColumnName = "code")
    @Where(clause = "state=0")
    @org.hibernate.annotations.OrderBy(clause = "update_time desc")
    private List<MenuLoanEntity> menuLoanEntities;

}
