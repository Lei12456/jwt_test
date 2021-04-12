package com.yl.admin.controller;

import cn.hutool.json.JSONObject;
import com.yl.admin.common.api.ResultCode;
import com.yl.admin.domain.TProductSubType;
import com.yl.admin.domain.TProductType;
import com.yl.admin.service.ProductSubTypeService;
import com.yl.admin.vo.CommonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/4
 */
@Api(tags = "ProductSubTypeController", description = "商品的分类管理界面")
@Controller
@RequestMapping("/productSubType")
@Slf4j
public class ProductSubTypeController {

    @Autowired
    private ProductSubTypeService productSubTypeService;

    @ApiOperation("查询所有商品分类子类列表")
    @RequestMapping(value = "/getAllProductSubType", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getAllProductSubType(){
        CommonVo commonVo = new CommonVo();
        try {
            List<TProductSubType> subTypeList = productSubTypeService.getAllSubType();
            commonVo.setResult(subTypeList);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("查询所有商品分类子类成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("查询所有商品分类子类失败");
        }
        return commonVo;
    }

    @ApiOperation("查询所有商品分类父类列表")
    @RequestMapping(value = "/getAllProductType", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getAllProductType(){
        CommonVo commonVo = new CommonVo();
        try {
            List<TProductType> typeList = productSubTypeService.getAllType();
            commonVo.setResult(typeList);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("查询所有商品父类成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("查询所有商品父类失败");
        }
        return commonVo;
    }

    @ApiOperation("查询指定父类的子类列表")
    @RequestMapping(value = "/getProductSubTypeByTypeId", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getProductSubTypeByTypeId(Long typeId){
        CommonVo commonVo = new CommonVo();
        try {
            List<TProductSubType> subTypeList = productSubTypeService.getProductSubTypeByTypeId(typeId);
            commonVo.setResult(subTypeList);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("查询所有商品父类成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("查询所有商品父类失败");
        }
        return commonVo;
    }

    @ApiOperation("分页查询子类型")
    @RequestMapping(value = "/getSubTypePage", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getSubTypePage(Integer page,Integer pageSize){
        CommonVo commonVo = new CommonVo();
        try {
            commonVo.setResult(productSubTypeService.getSubTypePage(page,pageSize));
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
        }
        return commonVo;
    }

    @ApiOperation("分页查询父类型")
    @RequestMapping(value = "/getTypePage", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getTypePage(Integer page,Integer pageSize){
        CommonVo commonVo = new CommonVo();
        try {
            commonVo.setResult(productSubTypeService.getTypePage(page,pageSize));
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
        }
        return commonVo;
    }

    @ApiOperation("添加")
    @RequestMapping(value = "/addOrUpdateType", method = RequestMethod.POST)
    @ResponseBody
    public CommonVo addOrUpdateType(@RequestBody JSONObject jsonObject){
        CommonVo commonVo = new CommonVo();
        try {
            JSONObject type = jsonObject.getJSONObject("type");
            JSONObject subType = jsonObject.getJSONObject("subType");
            if(subType != null){
                productSubTypeService.addOrUpdateSubType(subType);
            }else if (type != null){
                productSubTypeService.addOrUpdateType(type);
            }
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
        }
        return commonVo;
    }


    @ApiOperation("删除")
    @RequestMapping(value = "/deleteTypeOrSubType", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo deleteTypeOrSubType(@RequestParam(value = "subTypeId",required = false) Long subTypeId,@RequestParam(value = "typeId",required = false) Long typeId){
        CommonVo commonVo = new CommonVo();
        try {
            if(subTypeId != null){
                productSubTypeService.deleteSubType(subTypeId);
            }else if (typeId != null){
                productSubTypeService.deleteType(typeId);
            }
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
