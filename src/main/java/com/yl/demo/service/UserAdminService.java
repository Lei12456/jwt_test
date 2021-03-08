package com.yl.demo.service;

import com.yl.demo.domain.UserAdmin;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/8
 */
public interface UserAdminService {

    UserAdmin getUserByUsername(String username);
    void register(UserAdmin userAdmin);
}
