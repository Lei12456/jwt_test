package com.yl.demo.controller;

import com.yl.demo.common.api.ResultCode;
import com.yl.demo.common.util.JwtTokenUtil;
import com.yl.demo.domain.UserAdmin;
import com.yl.demo.service.UserAdminService;
import com.yl.demo.vo.CommonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/8
 */
@Api(tags = "LoginController", description = "登录界面")
@Controller
@RequestMapping("/userAdmin")
@Slf4j
public class LoginController {

    @Autowired
    private UserAdminService userAdminService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation("登录界面")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo login(String username ,String password){

        return null;
    }

    @ApiOperation("注册界面")
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo register(String username,String password){
        CommonVo commonVo = new CommonVo();
        try {
            UserAdmin userAdmin = new UserAdmin();
            userAdmin.setUsername(username);
            userAdmin.setPassword(password);
            userAdminService.register(userAdmin);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            logger.info("用户注册成功");
        }catch (Exception e){
            logger.error("用户注册失败",e);
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
        }
        return commonVo;
    }
}
