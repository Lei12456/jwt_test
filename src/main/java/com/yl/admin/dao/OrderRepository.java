package com.yl.admin.dao;

import com.yl.admin.domain.TOrder;
import com.yl.admin.domain.TProductOrderRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/9
 */
public interface OrderRepository extends JpaRepository<TOrder,Long> {

    @Query(value = "select to from t_order to")
    List<TOrder> getAllOrder();

    @Query(value = "select to from t_order to where to.number like %:number%")
    List<TOrder> getOrderByNumberLike(@Param("number") String number);

    @Query(value = "select * from t_order  limit :page,:pageSize",nativeQuery = true)
    List<TOrder> getOrderPage(@Param("page") Integer page , @Param("pageSize") Integer pageSize);

    @Query(value = "select * from t_order where status =:status limit :page,:pageSize",nativeQuery = true)
    List<TOrder>  getOrderByStatusPage(@Param("status") Integer status,@Param("page") Integer page , @Param("pageSize") Integer pageSize);

    @Query(value = "select * from t_order tor where tor.modify_time >=:startTime and tor.modify_time <=:endTime order by tor.modify_time desc " +
            "limit :page,:pageSize",nativeQuery = true)
    List<TOrder> getOrderBySubmitTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime,@Param("page") Integer page , @Param("pageSize") Integer pageSize);

    @Query(value = "select * from t_order tor where tor.modify_time >=:startTime and tor.modify_time <=:endTime and tor.status =:status order by tor.modify_time desc " +
            "limit :page,:pageSize ",nativeQuery = true)
    List<TOrder> getOrderBySubmitTimeAndStatus(@Param("startTime") Date startTime, @Param("endTime") Date endTime,@Param("page") Integer page ,
                                               @Param("pageSize") Integer pageSize,@Param("status") Integer status);


    @Modifying
    @Transactional
    @Query("update t_order set status =:status where id =:id")
    void updateOrderStatus(Integer status,Long id);
}
