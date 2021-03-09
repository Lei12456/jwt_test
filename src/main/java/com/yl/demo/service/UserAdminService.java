package com.yl.demo.service;

import com.yl.demo.domain.UserAdmin;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/8
 */
public interface UserAdminService {

    List<UserAdmin> getUserByUsername(String username);
    void register(UserAdmin userAdmin);
    String login(String username,String password);
}
