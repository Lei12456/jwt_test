package com.yl.demo.controller;

import com.yl.demo.common.util.JwtTokenUtil;
import com.yl.demo.domain.UserAdmin;
import com.yl.demo.service.UserAdminService;
import com.yl.demo.vo.CommonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Api(tags = "UserAdminController", description = "用户管理页面")
@Controller
@RequestMapping("/userAdmin")
@Slf4j
public class UserAdminController {

    @Autowired
    private UserAdminService userAdminService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation("获取用户信息")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('')")
    public CommonVo getUserInfo(String token){
        CommonVo commonVo = new CommonVo();
        try {
            UserAdmin user = userAdminService.getUserByToken(token);
        }catch (Exception e){

        }
        commonVo.setResult("恭喜您可以进入");
        return commonVo;
    }
}
