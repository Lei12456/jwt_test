package com.yl.demo.domain;

import lombok.Data;

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
@Table(name = "t_user_permission_relation)")
public class UserPermissionRelation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "permission_id")
    private Long permissionId;
}
