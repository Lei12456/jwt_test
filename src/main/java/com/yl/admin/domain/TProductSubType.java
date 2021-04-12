package com.yl.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/3
 */
@Data
@Entity(name = "t_product_sub_type")
public class TProductSubType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub_name")
    private String subName;

    @JoinColumn(name = "product_type_id")
    @ManyToOne
    @JsonIgnore
    private TProductType productType;

}
