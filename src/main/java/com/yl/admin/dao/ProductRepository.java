package com.yl.admin.dao;

import com.yl.admin.domain.TProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/** Description:
 *
 * @author YangLei
 * @date Created on 2021/4/3
 */
public interface ProductRepository extends JpaRepository<TProduct,Long> {

    @Query(value = "select tp from t_product tp")
    List<TProduct> getAllProduct();

    List<TProduct> getProductByNameLike(String keyWord);

    TProduct getTProductById(Long Id);

    @Query(value = "select * from t_product tp limit :page,:pageSize",nativeQuery = true)
    List<TProduct> getProductPage(@Param("page") Long page ,@Param("pageSize") Long pageSize);

    @Modifying
    @Transactional
    @Query(value = "update t_product tp set tp.flag =:flag,tp.status =:status where id =:id",nativeQuery = true)
    void updateProductStatusById(@Param("id") Long id,@Param("status") Integer status,@Param("flag") Integer flag);

}
