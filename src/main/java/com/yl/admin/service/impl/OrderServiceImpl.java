package com.yl.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONArray;
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
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public List<TOrder> getOrderByFilterPage(JSONObject jsonObject){
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
        JSONObject pageParams  = jsonObject.getJSONObject("pageParams");
        String pageStr = pageParams.get("page") != null ? pageParams.get("page").toString() : "1";
        String pageSizeStr = pageParams.get("pageSize") != null ? pageParams.get("pageSize").toString() : "5";
        Integer pageSize = Integer.parseInt(pageSizeStr);
        Integer page = (Integer.parseInt(pageStr) - 1) * pageSize;
        Integer status = Integer.parseInt(statusStr);
        if (status == -1 && submitTime.size() == 0){
            orderList = getOrderPage(page,pageSize);
        }else if(status != -1 &&  submitTime.size() == 0){
            orderList = orderRepository.getOrderByStatusPage(status, page, pageSize);
        }else if(submitTime.size() != 0 && status == -1){
            orderList = orderRepository.getOrderBySubmitTime(startTIme,endTime,page,pageSize);
        }else {
            orderList = orderRepository.getOrderBySubmitTimeAndStatus(startTIme,endTime,page,pageSize,status);
        }
        return orderList;
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
