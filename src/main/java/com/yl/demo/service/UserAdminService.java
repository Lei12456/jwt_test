package com.yl.demo.service;

import com.yl.demo.domain.UserAdmin;
import com.yl.demo.domain.UserPermission;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/8
 */
public interface UserAdminService {

    UserAdmin getUserByUsername(String username);
    void register(UserAdmin userAdmin);
    String login(String username,String password) throws Exception;
    List<UserPermission> getPermissionList(Long userId);
    UserAdmin getUserByToken(String token);

}
