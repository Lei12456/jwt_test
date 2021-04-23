package com.yl.admin.service.impl;

import com.yl.admin.common.util.DateUtil;
import com.yl.admin.dao.*;
import com.yl.admin.domain.*;
import com.yl.admin.service.IndexService;
import com.yl.admin.vo.StatisticalDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/15
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSubTypeRepository productSubTypeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductOrderRelaRepository productOrderRelaRepository;

    @Override
    public StatisticalDataVo getStatisticalData() {
        Long productSum = null;
        Long orderSum = null;
        Long subTypeSum =  null;
        Long totalSales = 0L;
        StatisticalDataVo statisticalDataVo = new StatisticalDataVo();
        List<TProduct> productList = productRepository.getAllProduct();
        List<TOrder> orderList = orderRepository.getAllOrder();
        List<TProductSubType> subTypeList = productSubTypeRepository.getAllProductSubType();
        List<TProductOrderRelation> productOrderRela= productOrderRelaRepository.findAll();
        //获取各项总数
        for (TProductOrderRelation relation : productOrderRela){
            Long priceProduct = productRepository.getTProductById(relation.getProductId()).getPrice();
            totalSales += priceProduct;
        }
        if(!CollectionUtils.isEmpty(productList)){
            Integer size = productList.size();
            productSum = size.longValue();
        }
        if(!CollectionUtils.isEmpty(orderList)){
            Integer size = orderList.size();
            orderSum = size.longValue();
        }
        if(!CollectionUtils.isEmpty(subTypeList)) {
            Integer size = subTypeList.size();
            subTypeSum = size.longValue();
        }
        //获取统计表的数据
        Object[] closeOrder = orderRepository.getTOrderByStatus(0);
        Object[]  deliveredOrder = orderRepository.getTOrderByStatus(1);
        Object[]  completedOrder = orderRepository.getTOrderByStatus(2);
        Map<String,Object> closeOrderMap = new TreeMap<>();
        Map<String,Object> deliveredOrderMap = new TreeMap<>();
        Map<String,Object> completedOrderMap = new TreeMap<>();
        for (Object o :closeOrder){
            Object[] arr = (Object[])o;
            closeOrderMap.put(arr[0].toString(),arr[1]);
        }
        for (Object o :deliveredOrder){
            Object[] arr = (Object[])o;
            deliveredOrderMap.put(arr[0].toString(),arr[1]);
        }
        for (Object o :completedOrder){
            Object[] arr = (Object[])o;
            completedOrderMap.put(arr[0].toString(),arr[1]);
        }
        statisticalDataVo.setProductSum(productSum);
        statisticalDataVo.setOrderSum(orderSum);
        statisticalDataVo.setSubTypeSum(subTypeSum);
        statisticalDataVo.setTotalSales(totalSales);
        statisticalDataVo.setCloseOrderMap(closeOrderMap);
        statisticalDataVo.setDeliveredOrderMap(deliveredOrderMap);
        statisticalDataVo.setCompletedOrderMap(completedOrderMap);
        return statisticalDataVo;
    }
}
