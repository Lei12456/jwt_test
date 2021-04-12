package com.yl.admin.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/2
 */
@Data
@Entity(name = "t_role")
public class TRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer status;

    // @OneToMany(mappedBy = "role",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    // private List<TUserAdmin> userAdmins;

}
