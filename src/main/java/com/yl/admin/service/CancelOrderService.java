package com.yl.admin.service;

import cn.hutool.json.JSONObject;
import com.yl.admin.domain.TCancelOrder;
import com.yl.admin.vo.CancelOrderDetailVo;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/11
 */
public interface CancelOrderService {

    List<TCancelOrder> getAllCancelOrder();

    List<TCancelOrder> getCancelOrderPage(Integer page,Integer pageSize);

    CancelOrderDetailVo getCancelOrderById(Long id);

    void updateCancelOrderStatus(Long id,Integer status);

    void updateCancelOrderChecked(Long id,Integer checked);

}
