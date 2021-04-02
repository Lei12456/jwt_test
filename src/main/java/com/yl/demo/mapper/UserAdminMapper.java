package com.yl.demo.mapper;

import com.yl.demo.domain.TUserAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/3/9
 */
@Mapper
public interface UserAdminMapper {

    List<TUserAdmin> getUserByUsername(@Param("username") String username);
    void register(TUserAdmin TUserAdmin);
}
