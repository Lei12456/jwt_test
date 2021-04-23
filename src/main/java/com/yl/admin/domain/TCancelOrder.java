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
@Entity(name = "t_cancel_order")
public class TCancelOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private TCustomer customer;

    @JoinColumn(name = "reason_id")
    @ManyToOne
    private TReason reason;

    @Column(name = "amount_return")
    private Long amountReturn;      //退款金额

    @Column(name = "create_time")
    private Date submitTime;        //退货订单提交时间

    private Integer status;

    @Column(name = "is_checked")
    private String isChecked;
}
