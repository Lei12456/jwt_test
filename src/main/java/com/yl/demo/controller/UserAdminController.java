package com.yl.demo.controller;

import com.yl.demo.common.api.ResultCode;
import com.yl.demo.domain.TUserAdmin;
import com.yl.demo.service.UserAdminService;
import com.yl.demo.common.util.FastDFSUtils;
import com.yl.demo.vo.CommonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private FastDFSUtils fastDFSUtils;



    @ApiOperation("获取用户信息")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('ONLY_READ')")
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

    @ApiOperation("上传用户头像")
    @RequestMapping(value = "/uploadUserHeader", method = RequestMethod.POST)
    @ResponseBody
    public CommonVo uploadUserHeader(@RequestParam("file") MultipartFile file,
                                     @RequestParam("username") String username){
        CommonVo commonVo = new CommonVo();
        try {
            String picture = fastDFSUtils.upload(file);
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

