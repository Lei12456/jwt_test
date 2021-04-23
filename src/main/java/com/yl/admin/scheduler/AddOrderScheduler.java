package com.yl.admin.scheduler;

import com.yl.admin.common.util.DateUtil;
import com.yl.admin.dao.CustomerRepository;
import com.yl.admin.dao.OrderRepository;
import com.yl.admin.dao.ProductOrderRelaRepository;
import com.yl.admin.dao.ProductRepository;
import com.yl.admin.domain.TCustomer;
import com.yl.admin.domain.TOrder;
import com.yl.admin.domain.TProductOrderRelation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/14
 */
@Slf4j
@Component
public class AddOrderScheduler {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductOrderRelaRepository productOrderRelaRepository;

    @Autowired
    private ProductRepository productRepository;

    @Scheduled(cron = "0 0 10,14,20 * * ?")
    //@Scheduled(fixedDelay = 1 * 1000)
    public void createOrder() {
        log.info("=======Start create order data==============");
        try {
            String DateStr = DateUtil.day2Str(new Date());
            String [] payMethodArr = {"支付宝","微信"};
            Long round = Math.round(Math.random() * 10);
            Long payRound = Math.round(Math.random() * 1);
            Integer payRandom = Integer.parseInt(payRound.toString());
            for(Long i = 1L; i <= round; i++){
                Long statusRound = Math.round(Math.random() * 2);
                TOrder order = new TOrder();
                String number = DateStr + "000" + i.toString();
                String payMethod = payMethodArr[payRandom];
                Long customerRound = Math.round(Math.random() * 7) + 1L;
                TCustomer customer = customerRepository.getOne(customerRound);
                Long amountSum = productRepository.getTProductById(3L).getPrice();
                order.setNumber(number);
                order.setPayMethod(payMethod);
                order.setCustomer(customer);
                order.setAmountSum(amountSum);
                order.setStatus(Integer.parseInt(statusRound.toString()));
                order.setModifyTime(new Date());
                order.setSubmitTime(new Date());
                orderRepository.save(order);
                //添加完订单之后在再往里面加product
                Long lastOrderId = orderRepository.getLastOrder().getId();
                TProductOrderRelation productOrderRelation = new TProductOrderRelation();
                productOrderRelation.setOrderId(lastOrderId);
                productOrderRelation.setProductId(3L);
                productOrderRelaRepository.save(productOrderRelation);
            }
        } catch (Throwable e) {
            log.error("Failed create order data", e);
        }
        log.info("=======End create order data===============");
    }

}
