package com.yl.admin.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/3
 */
@Data
@Entity(name = "t_order")
public class TOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;          //订单编号

    @Column(name = "pay_method")
    private String payMethod;       //支付方式

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private TCustomer customer;

    private Integer status;         //订单状态

    @Column(name = "amount_sum")
    private Long amountSum;         //订单总金额

    @Column(name = "create_time")   //订单提交时间
    private Date submitTime;

    @Column(name = "modify_time")   //订单更新时间
    private Date modifyTime;

    // @Column(name = "is_deleted")
    // private Integer isDeleted;
}
