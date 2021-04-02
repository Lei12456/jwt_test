package com.yl.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/9
 */
@Data
@Table(name = "t_permission")
@Entity
public class TUserPermission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pid")
    private Long pid;
    @Column(name = "value")
    private String value;
    @Column(name = "name")
    private String name;
    @Column(name = "icon")
    private String icon;
    @Column(name = "uri")
    private String uri;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "status")
    private Integer status;
}
