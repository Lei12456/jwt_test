package com.yl.admin.dao;

import com.yl.admin.domain.TProductCancelRelation;
import com.yl.admin.domain.TProductOrderRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/11
 */
public interface ProductCancelRelaRepository extends JpaRepository<TProductCancelRelation,Long> {

    @Query(value = "select * from t_product_cancel_relation where cancel_id =:cancelId",nativeQuery = true)
    List<TProductCancelRelation> getProductCancelRaByCancelId(@Param("cancelId") Long cancelId);
}
