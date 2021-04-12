package com.yl.admin.dao;

import com.yl.admin.domain.TReason;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/11
 */
public interface ReasonRepository extends JpaRepository<TReason,Long> {
}
