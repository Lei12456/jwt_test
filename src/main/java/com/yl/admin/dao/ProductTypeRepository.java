package com.yl.admin.dao;

import com.yl.admin.domain.TProductSubType;
import com.yl.admin.domain.TProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/3
 */
public interface ProductTypeRepository extends JpaRepository<TProductType,Long> {

    @Query(value = "select tp from t_product_type tp")
    List<TProductType> getAllProductType();

    @Query(value = "select * from t_product_type tps limit :page,:pageSize",nativeQuery = true)
    List<TProductType> getTypePage(Integer page, Integer pageSize);
}
