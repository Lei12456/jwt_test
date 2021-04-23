package com.yl.admin.dao;

import com.yl.admin.domain.TUserAdmin;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/30
 * */
public interface UserAdminRepository extends JpaRepository<TUserAdmin,Long> {

    TUserAdmin getUserAdminByUsername(String username);


    @Modifying
    @Transactional
    @Query("update t_user tu set tu.picture =:picture where tu.username=:username")
    void updateUserHeader(@Param("usename") String username,@Param("picture")String picture);
}
