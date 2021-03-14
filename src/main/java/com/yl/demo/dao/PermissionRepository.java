package com.yl.demo.dao;

import com.yl.demo.domain.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 *
 * @author angle
 * @date Created on 2021/3/13
 */
public interface PermissionRepository extends JpaRepository<UserPermission,Long> {
}
