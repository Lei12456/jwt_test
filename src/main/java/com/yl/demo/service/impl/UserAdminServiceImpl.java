package com.yl.demo.service.impl;

import com.yl.demo.dao.UserAdminMapper;
import com.yl.demo.domain.UserAdmin;
import com.yl.demo.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/8
 */
@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Autowired
    private UserAdminMapper userAdminMapper;

    @Override
    public List<UserAdmin> getUserByUsername(String username) {
        return null;
    }

    @Override
    public void register(UserAdmin userAdmin) {
        if (userAdmin.getUsername() != null) {
            List<UserAdmin> userAdmins = userAdminMapper.getUserByUsername(userAdmin.getUsername());
            if (CollectionUtils.isEmpty(userAdmins)) {
              userAdminMapper.register(userAdmin);
            }
        }
    }

    @Override
    public String login(String username, String password) {
        return null;
    }

}
