package com.yl.admin.dao;

import com.yl.admin.domain.TProductOrderRelation;
import com.yl.admin.domain.TSubTypeBrandRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/5/3
 */
public interface SubTypeBrandRelaRepository extends JpaRepository<TSubTypeBrandRelation, Long> {

    @Query(value = "select * from t_sub_type_brand_relation where sub_type_id =:subTypeId",nativeQuery = true)
    List<TSubTypeBrandRelation> getBrandBySubTypeId(@Param("subTypeId") Long subTypeId);
}
