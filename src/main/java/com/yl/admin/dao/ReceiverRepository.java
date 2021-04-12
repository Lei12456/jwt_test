package com.yl.admin.dao;

import com.yl.admin.domain.TReceiver;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/9
 */
public interface ReceiverRepository extends JpaRepository<TReceiver,Long> {

    @Query(value = "select distinct * from t_receiver tr where tr.customer_id =:customerId",nativeQuery = true)
    TReceiver getReceiverByCustomerId(@Param("customerId") Long customerId);
}
