package com.yl.admin.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/3
 */
@Data
@Entity(name = "t_reason")
public class TReason implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason;

    private Integer status;
}
