package com.myDemo.model.admin;

import lombok.*;

import java.util.Date;

/**
 * 角色表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RoleModel {
    private Integer roleId;
    private String roleName;
    private Integer createUserId;
    private Date createDate;
    private Integer modifyUserId;
    private Date modifyDate;

}
