package com.yl.admin.controller;

import com.yl.admin.common.api.ResultCode;
import com.yl.admin.domain.TBrand;
import com.yl.admin.dto.BrandDto;
import com.yl.admin.service.BrandService;
import com.yl.admin.vo.CommonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/4
 */
@Api(tags = "BrandController", description = "品牌管理界面")
@Controller
@RequestMapping("/brand")
@Slf4j
public class BrandController {

    @Autowired
    private BrandService brandService;

    @ApiOperation("查询所有商品品牌列表")
    @RequestMapping(value = "/getAllBrand", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getAllBrand(){
        CommonVo commonVo = new CommonVo();
        try {
            HashMap<String, Object> brandMap = new HashMap<>();
            List<TBrand> brandsList = brandService.getAllBrand();
            brandMap.put("brandsList",brandsList);
            brandMap.put("total",brandsList.size());
            commonVo.setResult(brandMap);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("查询所有品牌列表成功");
        }catch (Exception e){
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("查询所有品牌列表失败");
        }
        return commonVo;
    }

    // @ApiOperation("分页查询品牌")
    // @RequestMapping(value = "/getBrandPage", method = RequestMethod.GET)
    // @ResponseBody
    // public CommonVo getBrandPage(Long page,Long pageSize){
    //     CommonVo commonVo = new CommonVo();
    //     try {
    //         List<TBrand> brandPage = brandService.getBrandByPage(page, pageSize);
    //         commonVo.setResult(brandPage);
    //         commonVo.setCode(ResultCode.SUCCESS.getCode());
    //         commonVo.setMsg(ResultCode.SUCCESS.getMessage());
    //         log.info("分页查询品牌成功");
    //     }catch (Exception e){
    //         e.printStackTrace();
    //         commonVo.setCode(ResultCode.FAILED.getCode());
    //         commonVo.setMsg(ResultCode.FAILED.getMessage());
    //         log.error("分页查询品牌成功");
    //     }
    //     return commonVo;
    // }

    @ApiOperation("通过名字查询品牌")
    @RequestMapping(value = "/getBrandByName", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getBrandByName(String name,Long page,Long pageSize){
        CommonVo commonVo = new CommonVo();
        try {
            Map<String,Object> map = brandService.getBrandByNameLikePage(name, page, pageSize);
            commonVo.setResult(map);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("分页查询品牌成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("分页查询品牌成功");
        }
        return commonVo;
    }


    @ApiOperation("获取一个类型下的所有品牌")
    @RequestMapping(value = "/getBrandBySubtype", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getBrandBySubtype(Long sybTypeId){
        CommonVo commonVo = new CommonVo();
        try {
            List<TBrand> brandList = brandService.getBrandBySubType(sybTypeId);
            commonVo.setResult(brandList);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("");
        }
        return commonVo;
    }



    @ApiOperation("添加或者更新品牌")
    @RequestMapping(value = "/addOrUpdateBrand", method = RequestMethod.POST)
    @ResponseBody
    public CommonVo addOrUpdateProduct(@RequestBody BrandDto brand){
        CommonVo commonVo = new CommonVo();
        try {
            brandService.addOrUpdateBrand(brand);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            if(brand.getId() == null){
                log.info("添加品牌成功");
            }else {
                log.info("更新品牌成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("添加或更新品牌失败");
        }
        return commonVo;
    }

    @ApiOperation("删除品牌")
    @RequestMapping(value = "/deleteBrand", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo deleteBrand(Long id){
        CommonVo commonVo = new CommonVo();
        try {
            if(id != null) {
                brandService.deleteBrand(id);
                commonVo.setCode(ResultCode.SUCCESS.getCode());
                commonVo.setMsg(ResultCode.SUCCESS.getMessage());
                log.info("删除品牌成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("删除品牌失败");
        }
        return commonVo;
    }

}
