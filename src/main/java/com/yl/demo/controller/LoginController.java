package com.yl.demo.controller;

import com.yl.demo.common.api.ResultCode;
import com.yl.demo.common.util.JwtTokenUtil;
import com.yl.demo.service.UserAdminService;
import com.yl.demo.vo.CommonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/8
 */
@Api(tags = "LoginController", description = "登录界面")
@Controller
@RequestMapping("/userAdmin")
public class LoginController {

    @Autowired
    private UserAdminService userAdminService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation("登录界面")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo login(String username ,String password){

        return null;
    }

    @ApiOperation("注册界面")
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo register(String username , String password){
        CommonVo commonVo = new CommonVo();
        return commonVo;
    }
}
