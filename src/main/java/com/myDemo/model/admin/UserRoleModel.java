package com.myDemo.model.admin;

import lombok.*;

import java.util.Date;

/**
 * 用户角色表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserRoleModel {
    private Integer userRoleId;
    private Integer userId;
    private Integer roleId;

    private Integer createUserId;
    private Date createDate;
    private Integer modifyUserId;
    private Date modifyDate;

}
