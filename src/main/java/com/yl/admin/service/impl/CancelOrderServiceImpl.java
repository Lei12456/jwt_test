package com.yl.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yl.admin.dao.*;
import com.yl.admin.domain.*;
import com.yl.admin.dto.ProductDto;
import com.yl.admin.service.CancelOrderService;
import com.yl.admin.vo.CancelOrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/11
 */
@Service
public class CancelOrderServiceImpl implements CancelOrderService {

    @Autowired
    private CancelOrderRepository cancelOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCancelRelaRepository productCancelRelaRepository;

    @Autowired
    private ReasonRepository reasonRepository;

    @Override
    public List<TCancelOrder> getAllCancelOrder() {
        return cancelOrderRepository.getAllCancelOrder();
    }

    @Override
    public List<TCancelOrder> getCancelOrderPage(Integer page, Integer pageSize) {
        return cancelOrderRepository.getCancelOrderPage((page - 1) * pageSize,pageSize);
    }

    @Override
    public CancelOrderDetailVo getCancelOrderById(Long id) {
        List<ProductDto> products = new ArrayList<>();
        CancelOrderDetailVo cancelOrderDetailVo = new CancelOrderDetailVo();
        TCancelOrder cancel = cancelOrderRepository.getOne(id);
        List<TProductCancelRelation> productCancelList = productCancelRelaRepository.getProductCancelRaByCancelId(id);
        for (TProductCancelRelation productCancelRa : productCancelList){
            if(productCancelRa.getProductId() != null){
                ProductDto productDto = new ProductDto();
                TProduct product = productRepository.getOne(productCancelRa.getProductId());
                BeanUtil.copyProperties(product,productDto);
                products.add(productDto);
            }
        }
        BeanUtil.copyProperties(cancel,cancelOrderDetailVo);
        cancelOrderDetailVo.setReason(reasonRepository.getOne(1L).getReason());
        cancelOrderDetailVo.setCancelCus(cancel.getCustomer());
        cancelOrderDetailVo.setProducts(products);
        return cancelOrderDetailVo;
    }

    @Override
    public void updateCancelOrderStatus(Long id, Integer status) {
        cancelOrderRepository.updateCancelOrderStatus(status,id);
    }

    @Override
    public void updateCancelOrderChecked(Long id, Integer checked) {
        cancelOrderRepository.updateCancelOrderChecked(checked,id);
    }
}
