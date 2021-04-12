package com.yl.admin.service;

import com.yl.admin.domain.TRole;

import java.util.List;

/**
 * Description:角色管理逻辑层
 *
 * @author YangLei
 * @date Created on 2021/4/2
 */
public interface RoleService {
    /**
     * 查询所有角色
     */
    List<TRole> getAllRole();

    /**
     * 通过id查询角色
     * @param id
     * @return
     */
    TRole getRoleById(Long id);
 }

