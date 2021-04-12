package com.yl.admin.controller;

import cn.hutool.json.JSONObject;
import com.yl.admin.common.api.ResultCode;
import com.yl.admin.domain.TOrder;
import com.yl.admin.service.OrderService;
import com.yl.admin.vo.CommonVo;
import com.yl.admin.vo.OrderDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/9
 */
@Api(tags = "OrderController", description = "订单管理界面")
@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("查询所有订单列表")
    @RequestMapping(value = "/getAllOrder", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getAllOrder(){
        CommonVo commonVo = new CommonVo();
        try {
            List<TOrder> orderList= orderService.getAllOrder();
            commonVo.setResult(orderList);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("查询所有订单成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("查询所有订单失败");
        }
        return commonVo;
    }

    @ApiOperation("通过Id查询订单详情")
    @RequestMapping(value = "/getOrderById", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getOrderById(Long id){
        CommonVo commonVo = new CommonVo();
        try {
            OrderDetailVo orderDetailVo = orderService.getOrderById(id);
            commonVo.setResult(orderDetailVo);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("查询订单详情成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("查询订单详情成功");
        }
        return commonVo;
    }

    @ApiOperation("分页查询订单")
    @RequestMapping(value = "/getOrderPage", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getOrderPage(Integer page,Integer pageSize){
        CommonVo commonVo = new CommonVo();
        try {
            List<TOrder> orderList = orderService.getOrderPage(page, pageSize);
            commonVo.setResult(orderList);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("分页查询订单成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("分页查询订单失败");
        }
        return commonVo;
    }

    @ApiOperation("通过编号查询订单")
    @RequestMapping(value = "/getOrderByNumber", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getOrderByNumber(String number){
        CommonVo commonVo = new CommonVo();
        try {
            List<TOrder> orderList = orderService.getOrderByNumberLike(number);
            commonVo.setResult(orderList);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
        }
        return commonVo;
    }

    @ApiOperation("通过提交时间或状态查询查询订单")
    @RequestMapping(value = "/getOrderBySubmitOrStatusPage", method = RequestMethod.POST)
    @ResponseBody
    public CommonVo getOrderBySubmitOrStatusPage(@RequestBody JSONObject jsonObject){
        CommonVo commonVo = new CommonVo();
        try {
            List<TOrder> orderList = orderService.getOrderByFilterPage(jsonObject);
            commonVo.setResult(orderList);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
        }
        return commonVo;
    }

    @ApiOperation("删除订单")
    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonVo deleteOrder(@RequestBody List<Long> ids){
        CommonVo commonVo = new CommonVo();
        try {   
            if(!CollectionUtils.isEmpty(ids)){
                orderService.deleteOrder(ids);
            }
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("删除订单成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("删除订单失败");
        }
        return commonVo;
    }

    @ApiOperation("更改订单状态")
    @RequestMapping(value = "/updateOrderStatus", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo updateOrderStatus(Long id,Integer status){
        CommonVo commonVo = new CommonVo();
        try {
            orderService.updateOrderStatus(id,status);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("更改订单状态成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("更改订单状态失败");
        }
        return commonVo;
    }
}
