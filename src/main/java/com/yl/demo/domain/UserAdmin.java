package com.yl.demo.domain;

import lombok.Data;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/8
 */
@Data
public class UserAdmin {
    private Integer id;
    private String username;
    private String password;
    private Integer status;
}
