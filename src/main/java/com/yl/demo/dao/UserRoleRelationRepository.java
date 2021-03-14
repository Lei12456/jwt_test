package com.yl.demo.dao;

import com.yl.demo.domain.UserRoleRelation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 *
 * @author angle
 * @date Created on 2021/3/13
 */
public interface UserRoleRelationRepository extends JpaRepository<UserRoleRelation,Long> {
}
