package com.yl.demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/9
 */
@Data
public class UserPermission implements Serializable {
    private Long id;
    private Long pid;
    private String value;
    private String name;
    private String icon;
    private Date createTime;
    private Integer status;
}
