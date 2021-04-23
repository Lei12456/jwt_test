package com.yl.admin.service;

import com.yl.admin.domain.TUserAdmin;
import com.yl.admin.domain.TUserPermission;

import java.util.List;

/**
 * Description:用户管理逻辑层
 *
 * @author YangLei
 * @date Created on 2021/3/31
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
     * 更新用户头像
     */
    void updateUserHeader(String username,String picture);

    /**
     * 获取用户列表
     */
    List<TUserAdmin> getAllUserAdmin();
}
