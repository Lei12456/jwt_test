package com.yl.admin.domain;

import lombok.Data;
import org.hibernate.annotations.Proxy;

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
@Entity(name = "t_customer")
@Proxy(lazy = false)
public class TCustomer  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_account")
    private String customerAccount;

    private Integer status;

    private String telephone;
}
