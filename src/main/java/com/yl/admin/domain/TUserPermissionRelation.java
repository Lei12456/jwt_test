package com.yl.admin.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/13
 */
@Entity
@Data
@Table(name = "t_user_permission_relation)")
public class TUserPermissionRelation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "permission_id")
    private Long permissionId;
}
