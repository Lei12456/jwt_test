package com.yl.demo.dao;

import com.yl.demo.domain.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description:
 *
 * @author angle
 * @date Created on 2021/3/10
 */
public interface UserAdminRepository extends JpaRepository<UserAdmin,Long> {

    UserAdmin getUserAdminByUsername(String username);
}
