package com.yl.demo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/8
 */
@Data
public class CommonVo implements Serializable {
    private static final long serialVersionUID = -4906665999537007033L;

    private long code;
    private String msg;
    private Object result;
}
