package com.yl.admin.dao;

import com.yl.admin.domain.TUserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/31
 */
public interface PermissionRepository extends JpaRepository<TUserPermission,Long> {

    TUserPermission getUserPermissionById(Long id);
}
