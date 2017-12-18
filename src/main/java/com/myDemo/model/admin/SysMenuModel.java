package com.myDemo.model.admin;

import lombok.*;

/**
 * 系统菜单
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SysMenuModel {
    private Integer menuId;
    private String menuNo;
    private String applicationCode;
    private String menuName;


}
