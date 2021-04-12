package com.yl.admin.service.impl;

import com.yl.admin.dao.RoleRepository;
import com.yl.admin.domain.TRole;
import com.yl.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:角色逻辑层
 *
 * @author YangLei
 * @date Created on 2021/4/2
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<TRole> getAllRole() {
        return null;
    }

    @Override
    public TRole getRoleById(Long id) {
        return roleRepository.getTRoleById(id);
    }
}
