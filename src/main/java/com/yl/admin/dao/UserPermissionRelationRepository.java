package com.yl.admin.dao;

import com.yl.admin.domain.TUserPermissionRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/31
 */
public interface UserPermissionRelationRepository extends JpaRepository<TUserPermissionRelation,Long> {

    @Query(value = "select * from t_user_permission_relation where user_id =:userId",nativeQuery = true)
    List<TUserPermissionRelation> getUserPerReByUserId(@Param("userId") Long userId);
}
