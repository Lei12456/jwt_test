package com.yl.admin.controller;

import com.yl.admin.common.api.ResultCode;
import com.yl.admin.vo.CommonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/11
 */
@Api(tags = "IndexController", description = "首页统计图")
@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {

    @ApiOperation("获取数据数据")
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo login(String username , String password){
        CommonVo commonVo = new CommonVo();
        try {
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
        }catch (Exception e){
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
        }
        return commonVo;
    }

}
