package com.yl.admin.dao;

import com.yl.admin.domain.TBrand;
import com.yl.admin.dto.BrandDto;
import org.apache.ibatis.annotations.Param;
import org.hibernate.engine.jdbc.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/4
 */
public interface BrandRepository  extends JpaRepository<TBrand,Long> {

    @Query("select tb from t_brand tb")
    List<TBrand> getAllBrand();

    @Query(value = "select * from t_brand tp limit :page,:pageSize",nativeQuery = true)
    List<TBrand> getBrandPage(@Param("page") Long page , @Param("pageSize") Long pageSize);

    @Query(value = "select * from t_brand tb where tb.brand_name like %:name% limit :page,:pageSize ",nativeQuery = true)
    List<TBrand> getBrandByNameLike(@Param("name")String name, Long page, Long pageSize);

    @Query(value = "select count(*) from t_brand tb where tb.brand_name like %:name%",nativeQuery = true)
    Long getCountByNameLike(@Param("name")String name);

    @Query(value = "select * from t_brand tb order by id desc  limit 1",nativeQuery = true)
    TBrand getLastBrand();
}
