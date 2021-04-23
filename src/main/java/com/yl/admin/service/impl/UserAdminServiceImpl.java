package com.yl.admin.service.impl;

import com.yl.admin.common.util.JwtTokenUtil;
import com.yl.admin.dao.PermissionRepository;
import com.yl.admin.dao.UserAdminRepository;
import com.yl.admin.dao.UserPermissionRelationRepository;
import com.yl.admin.domain.TUserPermission;
import com.yl.admin.domain.TUserAdmin;
import com.yl.admin.domain.TUserPermissionRelation;
import com.yl.admin.service.UserAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/28
 */
@Service
@Slf4j
public class UserAdminServiceImpl implements UserAdminService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserAdminRepository userAdminRepository;

    @Autowired
    private UserPermissionRelationRepository userPermissionRelationRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public TUserAdmin getUserByUsername(String username) {
        return userAdminRepository.getUserAdminByUsername(username);
    }

    @Override
    public void register(TUserAdmin tUserAdmin) {
        tUserAdmin.setPicture("http://39.108.158.38:8888/group1/M00/00/00/J2yeJmBlet2AOuu3AAOazZefOmI24.jpeg");
        tUserAdmin.setStatus(4);
        userAdminRepository.save(tUserAdmin);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //List<UserAdmin> userAdminByUsername = userAdminRepository.getUserAdminByUsername(username);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!password.equals(userDetails.getPassword())){
            log.warn("密码不正确");
            throw new BadCredentialsException("密码不正确");
        }else {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        }
        return token;
    }

    @Override
    public List<TUserPermission> getPermissionList(Long userId) {
        List<TUserPermission> tUserPermissions =  new ArrayList<>();
        List<TUserPermissionRelation> permissionRelations = userPermissionRelationRepository.getUserPerReByUserId(userId);
        permissionRelations.forEach(permission -> {
            TUserPermission TUserPermission = permissionRepository.getUserPermissionById(permission.getPermissionId());
            if(TUserPermission != null){
                tUserPermissions.add(TUserPermission);
            }
        });
        return tUserPermissions;
    }

    @Override
    public TUserAdmin getUserByToken(String token) {
        String username = jwtTokenUtil.getUserNameFromToken(token);
        TUserAdmin tUserAdmin = userAdminRepository.getUserAdminByUsername(username);
        return tUserAdmin;
    }

    @Override
    public void updateUserHeader(String username, String picture) {
        userAdminRepository.updateUserHeader(username,picture);
    }

    @Override
    public List<TUserAdmin> getAllUserAdmin() {
        return  userAdminRepository.findAll();
    }

}
