package com.yl.admin.controller;

import cn.hutool.json.JSONObject;
import com.yl.admin.common.api.ResultCode;
import com.yl.admin.domain.TProduct;
import com.yl.admin.service.ProductService;
import com.yl.admin.vo.CommonVo;
import com.yl.admin.dto.ProductDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/3
 */
@Api(tags = "ProductController", description = "商品管理界面")
@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("查询所有商品列表")
    @RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getAllProduct(){
        CommonVo commonVo = new CommonVo();
        try {
            List<TProduct> productList = productService.getAllProduct();
            commonVo.setResult(productList);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("查询所有商品成功");
        }catch (Exception e){
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("查询所有商品失败");
        }
        return commonVo;
    }

    @ApiOperation("通过Id查询商品详情")
    @RequestMapping(value = "/getProductById", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getProductById(Long id){
        CommonVo commonVo = new CommonVo();
        try {
            TProduct product = productService.getProductById(id);
            commonVo.setResult(product);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("查询商品详情成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("查询所有商品失败");
        }
        return commonVo;
    }

    @ApiOperation("分页查询商品")
    @RequestMapping(value = "/getProductPage", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getProductPage(Integer currentPage,Long pageSize){
        CommonVo commonVo = new CommonVo();
        try {
            List<TProduct> productsPage = productService.getProductPage(currentPage, pageSize);
            commonVo.setResult(productsPage);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("分页查询商品成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("分页查询商品失败");
        }
        return commonVo;
    }

    @ApiOperation("根据品牌或类别或名称模糊查询查询商品")
    @RequestMapping(value = "/getProductByFilter", method = RequestMethod.POST)
    @ResponseBody
    public CommonVo getProductByFilter(@RequestBody JSONObject jsonObject){
        CommonVo commonVo = new CommonVo();
        try {
            List<TProduct> productList= productService.getProductByFilter(jsonObject);
            commonVo.setResult(productList);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("查询商品成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("查询商品失败");
        }
        return commonVo;
    }

    @ApiOperation("添加或者更新商品")
    @RequestMapping(value = "/addOrUpdateProduct", method = RequestMethod.POST)
    @ResponseBody
    public CommonVo addOrUpdateProduct(@RequestBody ProductDto productDto){
        CommonVo commonVo = new CommonVo();
        try {
            productService.addOrUpdateProduct(productDto);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            if(productDto.getId() == null){
                log.info("添加商品成功");
            }else {
                log.info("更新商品成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("添加或更新商品商品失败");
        }
        return commonVo;
    }

    @ApiOperation("更新商品状态")
    @RequestMapping(value = "/updateProductStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonVo updateProductStatus(Long id,Integer status,Integer flag){
        CommonVo commonVo = new CommonVo();
        try {
            productService.updateProductById(id,status,flag);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
        }
        return commonVo;
    }

    @ApiOperation("删除商品")
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    @ResponseBody
    public CommonVo deleteProduct(@RequestBody List<Long> ids){
        CommonVo commonVo = new CommonVo();
        try {
            productService.deleteProduct(ids);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("删除商品成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.info("删除商品失败");
        }
        return commonVo;
    }

}
