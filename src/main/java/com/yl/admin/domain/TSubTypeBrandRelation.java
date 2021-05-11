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
@Table(name = "t_sub_type_brand_relation")
public class TSubTypeBrandRelation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub_type_id")
    private Long subTypeId;

    @Column(name = "brand_id")
    private Long brandId;
}
