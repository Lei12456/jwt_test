package com.yl.admin.domain;

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
@Entity(name = "t_product")
public class TProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String picture;//商品图片

    private Long price;    //商品价格

    private Integer flag; //商品标签

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TProductSubType productSubType;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private TBrand brand;

    private Long stock;   //商品库存

    @Column(name = "sales_volume")
    private Long  salesVolume; //商品销量

    private Integer status;
}
