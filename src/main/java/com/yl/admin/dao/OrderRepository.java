package com.yl.admin.dao;

import com.yl.admin.domain.TOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    /**
     * 获取total
     */
    @Query(value = "select count(*) from t_order where status =:status",nativeQuery = true)
    Integer getTOrderByStatusCount(Integer status);

    @Query(value = "select count(*) from t_order tor where tor.create_time >=:startTime and tor.create_time <=:endTime",nativeQuery = true)
    Integer getTOrderBySubmitCount(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Query(value = "select count(*) from t_order tor where tor.create_time >=:startTime and tor.create_time <=:endTime and status=:status",nativeQuery = true)
    Integer getTOrderByCount(@Param("startTime") Date startTime, @Param("endTime") Date endTime,Integer status);

    /**
     *分页查询
     */
    @Query(value = "select * from t_order where status =:status limit :page,:pageSize",nativeQuery = true)
    List<TOrder>  getOrderByStatusPage(@Param("status") Integer status,@Param("page") Integer page , @Param("pageSize") Integer pageSize);

    @Query(value = "select * from t_order tor where tor.create_time >=:startTime and tor.create_time <=:endTime order by tor.create_time desc " +
            "limit :page,:pageSize",nativeQuery = true)
    List<TOrder> getOrderBySubmitTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime,@Param("page") Integer page , @Param("pageSize") Integer pageSize);

    @Query(value = "select * from t_order tor where tor.create_time >=:startTime and tor.create_time <=:endTime and tor.status =:status order by tor.create_time desc " +
            "limit :page,:pageSize ",nativeQuery = true)
    List<TOrder> getOrderBySubmitTimeAndStatus(@Param("startTime") Date startTime, @Param("endTime") Date endTime,@Param("page") Integer page ,
                                               @Param("pageSize") Integer pageSize,@Param("status") Integer status);


    /**
     * 查询最后一条订单
     * @return
     */
    @Query(value = "select * from  t_order tor order by tor.id desc limit 1" ,nativeQuery = true)
    TOrder getLastOrder();


    /**
     * 查询最近七天的订单
     * @param status
     * @return
     */
    @Query(value = "SELECT DATE_FORMAT(create_time, '%Y-%m-%d' ) days, count(*) count\n" +
            "FROM( SELECT *  FROM t_order tor where tor.status =:status) as te\n" +
            "GROUP BY days order by days desc limit 7;",nativeQuery = true)
    Object[] getTOrderByStatus(Integer status);

    @Modifying
    @Transactional
    @Query("update t_order set status =:status where id =:id")
    void updateOrderStatus(Integer status,Long id);
}
