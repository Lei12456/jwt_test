package com.yl.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Entity(name = "t_brand")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class TBrand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name")
    private String  brandName;

    private String  logo;

    private String  operators;

    private String  description;

    @Column(name = "English_name")
    private String  EnglishName;

}
