package com.yl.admin.controller;

import com.yl.admin.common.api.ResultCode;
import com.yl.admin.domain.TUserAdmin;
import com.yl.admin.service.RoleService;
import com.yl.admin.service.UserAdminService;
import com.yl.admin.common.util.FastDFSUtils;
import com.yl.admin.vo.CommonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Logger;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/31
 */
@Api(tags = "UserAdminController", description = "用户管理页面")
@Controller
@RequestMapping("/userAdmin")
@Slf4j
public class UserAdminController {

    @Autowired
    private UserAdminService userAdminService;

    @ApiOperation("获取用户信息")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getUserInfo(String token){
        CommonVo commonVo = new CommonVo();
        try {
            TUserAdmin userInfo = userAdminService.getUserByToken(token);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setResult(userInfo);
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("成功获取用户信息");
        }catch (Exception e){
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.info("用户信息获取失败");
        }
        return commonVo;
    }


    @ApiOperation("获取用户列表")
    @RequestMapping(value = "/getAllUserAdmin", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getAllUserAdmin(){
        CommonVo commonVo = new CommonVo();
        try {
            List<TUserAdmin> allUserAdmin = userAdminService.getAllUserAdmin();
            commonVo.setResult(allUserAdmin);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("成功获取用户信息");
        }catch (Exception e){
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.info("用户信息获取失败");
        }
        return commonVo;
    }

    @ApiOperation("上传用户头像")
    @RequestMapping(value = "/uploadUserHeader", method = RequestMethod.POST)
    @ResponseBody
    public CommonVo uploadUserHeader(@RequestParam("picture") String picture,
                                     @RequestParam("username") String username){
        CommonVo commonVo = new CommonVo();
        try {
            userAdminService.updateUserHeader(username,picture);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg("上传头像成功");
            log.info("上传成功");
        }catch (Exception e){
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setResult("");
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.info("上传失败");
        }
        return commonVo;
    }
}

