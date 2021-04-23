package com.yl.admin.controller;

import com.yl.admin.common.api.ResultCode;
import com.yl.admin.service.IndexService;
import com.yl.admin.vo.CommonVo;
import com.yl.admin.vo.StatisticalDataVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/11
 */
@Api(tags = "IndexController", description = "首页统计图")
@Controller
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @Autowired
    private IndexService indexService;


    @ApiOperation("获取数据")
    @RequestMapping(value = "/showData", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo showData(){
        CommonVo commonVo = new CommonVo();
        try {
            StatisticalDataVo statisticalData = indexService.getStatisticalData();
            commonVo.setResult(statisticalData);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
        }
        return commonVo;
    }

}
