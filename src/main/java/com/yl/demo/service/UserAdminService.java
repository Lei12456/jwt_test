package com.yl.demo.service;

import com.yl.demo.domain.TUserAdmin;
import com.yl.demo.domain.TUserPermission;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/8
 */
public interface UserAdminService {
    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    TUserAdmin getUserByUsername(String username);

    /**
     * 注册用户
     * @param TUserAdmin
     */
    void register(TUserAdmin TUserAdmin);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    String login(String username,String password) throws Exception;

    /**
     * 获取用户权限礼列表
     * @param userId
     * @return
     */
    List<TUserPermission> getPermissionList(Long userId);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    TUserAdmin getUserByToken(String token);
    /**
     *
     */
    void updateUserHeader(String username,String picture);
}
