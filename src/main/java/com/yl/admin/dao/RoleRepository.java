package com.yl.admin.dao;

import com.yl.admin.domain.TRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/2
 */
public interface RoleRepository extends JpaRepository<TRole,Long> {

    TRole getTRoleById(Long id);
}
