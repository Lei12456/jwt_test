package com.yl.admin.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/9
 */
@Data
@Entity
@Table(name = "t_product_order_relation")
public class TProductOrderRelation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "order_id")
    private Long orderId;
}
