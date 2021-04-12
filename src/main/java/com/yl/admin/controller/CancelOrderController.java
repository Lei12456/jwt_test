package com.yl.admin.controller;


import com.yl.admin.common.api.ResultCode;
import com.yl.admin.domain.TCancelOrder;
import com.yl.admin.service.CancelOrderService;
import com.yl.admin.vo.CancelOrderDetailVo;
import com.yl.admin.vo.CommonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/11
 */
@Api(tags = "OrderController", description = "订单管理界面")
@Controller
@RequestMapping("/cancelOrder")
@Slf4j
public class CancelOrderController {

    @Autowired
    private CancelOrderService cancelOrderService;

    @ApiOperation("查询所有退货列表")
    @RequestMapping(value = "/getAllCancelOrder", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getAllCancelOrder(){
        CommonVo commonVo = new CommonVo();
        try {
            List<TCancelOrder> orderList= cancelOrderService.getAllCancelOrder();
            commonVo.setResult(orderList);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("查询所有退货成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("查询所有退货失败");
        }
        return commonVo;
    }

    @ApiOperation("通过Id查询退货详情")
    @RequestMapping(value = "/getCancelOrderById", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getCancelOrderById(Long id){
        CommonVo commonVo = new CommonVo();
        try {
            CancelOrderDetailVo orderDetailVo = cancelOrderService.getCancelOrderById(id);
            commonVo.setResult(orderDetailVo);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("查询退货详情成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("查询退货详情成功");
        }
        return commonVo;
    }

    @ApiOperation("分页查询退货")
    @RequestMapping(value = "/getCancelOrderPage", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo getCancelOrderPage(Integer page,Integer pageSize){
        CommonVo commonVo = new CommonVo();
        try {
            List<TCancelOrder> orderList = cancelOrderService.getCancelOrderPage(page, pageSize);
            commonVo.setResult(orderList);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("分页查询退货成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("分页查询退货失败");
        }
        return commonVo;
    }


    @ApiOperation("更改退货状态")
    @RequestMapping(value = "/updateCancelOrderStatus", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo updateCancelOrderStatus(Long id,Integer status){
        CommonVo commonVo = new CommonVo();
        try {
            cancelOrderService.updateCancelOrderStatus(id,status);
            commonVo.setCode(ResultCode.SUCCESS.getCode());
            commonVo.setMsg(ResultCode.SUCCESS.getMessage());
            log.info("更改退货状态成功");
        }catch (Exception e){
            e.printStackTrace();
            commonVo.setCode(ResultCode.FAILED.getCode());
            commonVo.setMsg(ResultCode.FAILED.getMessage());
            log.error("更改退货状态失败");
        }
        return commonVo;
    }

    @ApiOperation("审核退货订单")
    @RequestMapping(value = "/updateCancelOrderCheck", method = RequestMethod.GET)
    @ResponseBody
    public CommonVo updateCancelOrderCheck(Long id,Integer checked){
        CommonVo commonVo = new CommonVo();
        try {
            cancelOrderService.updateCancelOrderChecked(id,checked);
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
