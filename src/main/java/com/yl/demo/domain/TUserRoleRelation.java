package com.yl.demo.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Description:
 *
 * @author angle
 * @date Created on 2021/3/13
 */
@Entity
@Data
@Table(name = "t_user_role_relation")
public class TUserRoleRelation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "role_id")
    private Integer roleId;
}
