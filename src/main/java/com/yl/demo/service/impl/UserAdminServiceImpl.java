package com.yl.demo.service.impl;

import com.yl.demo.common.util.JwtTokenUtil;
import com.yl.demo.dao.PermissionRepository;
import com.yl.demo.dao.UserAdminRepository;
import com.yl.demo.dao.UserPermissionRelationRepository;
import com.yl.demo.domain.UserPermission;
import com.yl.demo.domain.UserAdmin;
import com.yl.demo.domain.UserPermissionRelation;
import com.yl.demo.service.UserAdminService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
 * @date Created on 2021/3/8
 */
@Service
@Slf4j
public class UserAdminServiceImpl implements UserAdminService {

    // @Autowired
    // private UserAdminMapper userAdminMapper;
    private Logger logger = LoggerFactory.getLogger(UserAdminServiceImpl.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserAdminRepository userAdminRepository;

    @Autowired
    private UserPermissionRelationRepository userPermissionRelationRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserAdmin getUserByUsername(String username) {
        return userAdminRepository.getUserAdminByUsername(username);
    }

    @Override
    public void register(UserAdmin userAdmin) {
        if (userAdmin.getUsername() != null) {
            UserAdmin users = userAdminRepository.getUserAdminByUsername(userAdmin.getUsername());
            userAdmin.setStatus(4);
            userAdminRepository.save(userAdmin);
        }
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //List<UserAdmin> userAdminByUsername = userAdminRepository.getUserAdminByUsername(username);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!password.equals(userDetails.getPassword())){
            logger.warn("密码不正确");
            throw new BadCredentialsException("密码不正确");
        }else {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            boolean authenticated = authentication.isAuthenticated();
            token = jwtTokenUtil.generateToken(userDetails);
        }
        return token;
    }

    @Override
    public List<UserPermission> getPermissionList(Long userId) {
        List<UserPermission> userPermissions =  new ArrayList<>();
        List<UserPermissionRelation> permissionRelations = userPermissionRelationRepository.getUserPerReByUserId(userId);
        permissionRelations.forEach(permission -> {
            UserPermission userPermission = permissionRepository.getOne(permission.getPermissionId());
            if(userPermission != null){
                userPermissions.add(userPermission);
            }
        });
        return userPermissions;
    }

    @Override
    public UserAdmin getUserByToken(String token) {
        String username = jwtTokenUtil.getUserNameFromToken(token);
        UserAdmin userAdmin = userAdminRepository.getUserAdminByUsername(username);
        return userAdmin;
    }

}
