package com.yl.admin.dao;

import com.yl.admin.domain.TCancelOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/11
 */
public interface CancelOrderRepository extends JpaRepository<TCancelOrder,Long> {

    @Query(value = "select to from t_cancel_order to")
    List<TCancelOrder> getAllCancelOrder();

    @Query(value = "select * from t_cancel_order  limit :page,:pageSize",nativeQuery = true)
    List<TCancelOrder> getCancelOrderPage(@Param("page") Integer page , @Param("pageSize") Integer pageSize);

    @Modifying
    @Transactional
    @Query("update t_cancel_order set status =:status where id =:id")
    void updateCancelOrderStatus(Integer status,Long id);

    @Modifying
    @Transactional
    @Query(value = "update t_cancel_order tco set tco.is_checked =:checked where tco.id =:id",nativeQuery = true)
    void updateCancelOrderChecked(Integer checked,Long id);
}
