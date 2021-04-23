package com.yl.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.yl.admin.common.util.DateUtil;
import com.yl.admin.dao.OrderRepository;
import com.yl.admin.dao.ProductOrderRelaRepository;
import com.yl.admin.dao.ProductRepository;
import com.yl.admin.dao.ReceiverRepository;
import com.yl.admin.domain.TOrder;
import com.yl.admin.domain.TProduct;
import com.yl.admin.domain.TProductOrderRelation;
import com.yl.admin.domain.TReceiver;
import com.yl.admin.dto.ProductDto;
import com.yl.admin.service.OrderService;
import com.yl.admin.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/9
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOrderRelaRepository productOrderRelaRepository;

    @Autowired
    private ReceiverRepository receiverRepository;

    @Override
    public List<TOrder> getAllOrder() {
        return orderRepository.getAllOrder();
    }

    @Override
    public List<TOrder> getOrderPage(Integer page, Integer pageSize) {
        return orderRepository.getOrderPage(page ,pageSize);
    }

    @Override
    public  Map<String,Object> getOrderByFilterPage(JSONObject jsonObject){
        Map<String,Object> completeMap =  new HashMap<>();
        List<TOrder> orderList = new ArrayList<>();
        //List<String> submitTime = new ArrayList<>();
        Date startTIme = null;
        Date endTime = null;
        String statusStr = jsonObject.get("status") != ""? jsonObject.get("status").toString() : "-1";
        List<String> submitTime = jsonObject.get("submitTime", ArrayList.class);
        if (!CollectionUtils.isEmpty(submitTime)){
            startTIme = DateUtil.str2Day(submitTime.get(0));
            endTime = DateUtil.str2Day(submitTime.get(1));
        }
        //获取到所要筛选的参数
        JSONObject pageParams  = jsonObject.getJSONObject("pageParams");
        String pageStr = pageParams.get("page") != null ? pageParams.get("page").toString() : "1";
        String pageSizeStr = pageParams.get("pageSize") != null ? pageParams.get("pageSize").toString() : "5";
        Integer pageSize = Integer.parseInt(pageSizeStr);
        Integer page = (Integer.parseInt(pageStr) - 1) * pageSize;
        Integer status = Integer.parseInt(statusStr);
        //下面开始筛选
        if (status == -1 && CollectionUtils.isEmpty(submitTime)){
            orderList = getOrderPage(page,pageSize);
            List<TOrder> allOrder = getAllOrder();
            if(!CollectionUtils.isEmpty(allOrder)){
              completeMap.put("total",allOrder.size());
                completeMap.put("orderList",orderList);
            }
        }else if(status != -1 &&  CollectionUtils.isEmpty(submitTime)){
            orderList = orderRepository.getOrderByStatusPage(status, page, pageSize);
            Integer total = orderRepository.getTOrderByStatusCount(status);
            completeMap.put("total",total);
            completeMap.put("orderList",orderList);
            System.out.println();
        }else if(!CollectionUtils.isEmpty(submitTime) && status == -1){
            orderList = orderRepository.getOrderBySubmitTime(startTIme,endTime,page,pageSize);
            Integer total = orderRepository.getTOrderBySubmitCount(startTIme,endTime);
            completeMap.put("total",total);
            completeMap.put("orderList",orderList);
            System.out.println();
        }else {
            orderList = orderRepository.getOrderBySubmitTimeAndStatus(startTIme,endTime,page,pageSize,status);
            Integer total = orderRepository.getTOrderByCount(startTIme,endTime,status);
            completeMap.put("total",total);
            completeMap.put("orderList",orderList);
        }
        return completeMap;
    }
    @Override
    public List<TOrder> getOrderByNumberLike(String number) {
        return orderRepository.getOrderByNumberLike(number);
    }

    @Override
    public OrderDetailVo getOrderById(Long id) {
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        List<ProductDto> productList = new ArrayList();
        TOrder order = orderRepository.getOne(id);
        BeanUtil.copyProperties(order,orderDetailVo);
        List<TProductOrderRelation> productOrderRaList = productOrderRelaRepository.getProductOrderRaByOrderId(id);
        for (TProductOrderRelation productOrderRa : productOrderRaList){
            if(productOrderRa.getProductId() != null){
                ProductDto productDto = new ProductDto();
                TProduct product = productRepository.getOne(productOrderRa.getProductId());
                BeanUtil.copyProperties(product,productDto);
                productList.add(productDto);
            }
        }
        if (order.getCustomer().getId() != null){
            TReceiver receiver = receiverRepository.getReceiverByCustomerId(order.getCustomer().getId());
            orderDetailVo.setReceiver(receiver);
        }
        orderDetailVo.setProducts(productList);
        return orderDetailVo;
    }

    @Override
    public void deleteOrder(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            for (Long id : ids) {
                orderRepository.deleteById(id);
            }
        }
    }

    @Override
    public void updateOrderStatus(Long id, Integer status) {
        orderRepository.updateOrderStatus(status,id);
    }

}
