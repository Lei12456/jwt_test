package com.yl.demo.dao;

import com.yl.demo.domain.UserAdmin;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/9
 */
public interface UserAdminMapper {
    List<UserAdmin> getUserByUsername(String username);
    void register(UserAdmin userAdmin);
}
