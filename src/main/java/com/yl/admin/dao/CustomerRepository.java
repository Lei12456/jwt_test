package com.yl.admin.dao;

import com.yl.admin.domain.TCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/14
 */
public interface CustomerRepository extends JpaRepository<TCustomer,Long> {
}
