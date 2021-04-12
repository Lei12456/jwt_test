package com.yl.admin.service;

import cn.hutool.json.JSONObject;
import com.yl.admin.domain.TOrder;
import com.yl.admin.vo.OrderDetailVo;

import java.util.Date;
import java.util.List;

/**
 * Description:订单管理接口
 *
 * @author YangLei
 * @date Created on 2021/4/9
 */
public interface OrderService {
    /**
     * 所有订单列表
     */
    List<TOrder> getAllOrder();
    /**
     * 分页所有查询订单
     */
    List<TOrder> getOrderPage(Integer page,Integer pageSize);
    /**
     * 通过订单编号查询
     */
    List<TOrder> getOrderByNumberLike(String number);
    /**
     * 通过id查询
     */
    OrderDetailVo getOrderById(Long id);
    /**
     * 删除订单
     */
    void deleteOrder(List<Long> ids);
    /**
     * 更新订单状态
     */
    void updateOrderStatus(Long id,Integer status);
    /**
     * 分页查询带过滤字段的订单
     */
    List<TOrder> getOrderByFilterPage(JSONObject jsonObject);
}
