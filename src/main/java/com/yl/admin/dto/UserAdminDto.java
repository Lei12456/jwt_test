package com.yl.admin.dto;
import io.swagger.annotations.ApiParam;
import lombok.Data;


/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/1
 */

@Data
public class UserAdminDto {

    @ApiParam(value = "id")
    private Long id;
    @ApiParam(value = "用户名",required = true)
    private String username;
    @ApiParam(value = "别称",required = true)
    private String displayName;
    @ApiParam(value = "密码",required = true)
    private String password;
    @ApiParam(value = "头像",required = true)
    private String picture;
    @ApiParam(value = "所属角色",required  =true)
    private Long roleId;
}
