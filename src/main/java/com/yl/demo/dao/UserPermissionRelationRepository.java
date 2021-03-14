package com.yl.demo.dao;

import com.yl.demo.domain.UserPermissionRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description:
 *
 * @author angle
 * @date Created on 2021/3/13
 */
public interface UserPermissionRelationRepository extends JpaRepository<UserPermissionRelation,Long> {

    //@Query("select upr from UserPermissionRelation upr where upr.userId =:userId")
    @Query(value = "select * from t_user_permission_relation where user_id =:userId",nativeQuery = true)
    List<UserPermissionRelation> getUserPerReByUserId(@Param("userId") Long userId);
}
