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
@Entity(name = "t_receiver")
public class TReceiver implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "receiver_name")
    private String receiverName;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private TCustomer customer;

    private String address;

    private Integer status;

    private String telephone;

    private String postcode;
}
