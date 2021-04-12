package com.yl.admin.dao;

import com.yl.admin.domain.TProductSubType;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/3
 */
public interface ProductSubTypeRepository extends JpaRepository<TProductSubType,Long> {

    @Query(value = "select tps from t_product_sub_type tps")
    List<TProductSubType> getAllProductSubType();

    // @Query("select tps from t_product_sub_type tps where tps.productType =:typeId")
    // List<TProductSubType> getTProductSubTypeByTypeId(@Param("typeId") Long Id);

    @Query(value = "select * from t_product_sub_type tps limit :page,:pageSize",nativeQuery = true)
    List<TProductSubType> getSubTypePage(Integer page,Integer pageSize);
}
