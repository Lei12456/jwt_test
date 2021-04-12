package com.yl.admin.dao;

import com.yl.admin.domain.TProductOrderRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/9
 */
public interface ProductOrderRelaRepository extends JpaRepository<TProductOrderRelation,Long> {

    @Query(value = "select * from t_product_order_relation where order_id =:orderId",nativeQuery = true)
    List<TProductOrderRelation> getProductOrderRaByOrderId(@Param("orderId") Long orderId);
}
