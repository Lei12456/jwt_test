package com.yl.admin.controller;

import com.yl.admin.common.api.ResultCode;
import com.yl.admin.domain.TUserAdmin;
import com.yl.admin.dto.UserAdminDto;
import com.yl.admin.service.UserAdminService;
import com.yl.admin.vo.CommonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/31
 */
@Api(tags = "LoginController", description = "登录界面")
@Controller
@RequestMapping("/")
@Slf4j
public class LoginController {

    @Autowired
    private UserAdminService userAdminService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("登录界面")
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo login(String username ,String password){
        CommonVo commonVo = new CommonVo();
        try {
            Map<String, Object> tokenMap = new HashMap<>();
            String token = userAdminService.login(username, password);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            tokenMap.put("tokenHead",tokenHead);
            //tokenMap.put("token",tokenHead+" "+token);
            tokenMap.put("token",token);
            commonVo.setResult(tokenMap);
            log.info("登录成功");
        }catch (Exception e){
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("登录失败");
        }
        return commonVo;
    }

    @ApiOperation("注册界面")
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public CommonVo register(UserAdminDto userAdminDto){
        CommonVo commonVo = new CommonVo();
        try {
            TUserAdmin userAdmin = new TUserAdmin();
            BeanUtils.copyProperties(userAdminDto, userAdmin);
            if (userAdminDto.getUsername() != null) {
                TUserAdmin user = userAdminService.getUserByUsername(userAdminDto.getUsername());
                if (user == null){
                    userAdminService.register(userAdmin);
                    log.info("用户注册成功");
                    commonVo.setCode(ResultCode.SUCCESS.getCode());
                    commonVo.setMsg(ResultCode.SUCCESS.getMessage());
                }else {
                    log.warn("用户名已存在");

                    commonVo.setCode(ResultCode.FAILED.getCode());
                    commonVo.setMsg("用户名已存在");
                }
            }
        }catch (Exception e){
            log.error("用户注册失败",e);
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
        }
        return commonVo;
    }
}
