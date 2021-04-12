package com.yl.admin.controller;

import com.yl.admin.common.api.ResultCode;
import com.yl.admin.common.util.FastDFSUtils;
import com.yl.admin.domain.TUserAdmin;
import com.yl.admin.vo.CommonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date Created on 2021/4/5
 */
@Api(tags = "uploadPictureController", description = "上传图片")
@Controller
@RequestMapping("/file")
@Slf4j
public class uploadPictureController {
    @Autowired
    private FastDFSUtils fastDFSUtils;

    @ApiOperation("上传图片")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CommonVo upload(@RequestParam("file") MultipartFile file){
        CommonVo commonVo = new CommonVo();
        try {
            String filePath = fastDFSUtils.upload(file);
            commonVo.setResult(filePath);
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
}
